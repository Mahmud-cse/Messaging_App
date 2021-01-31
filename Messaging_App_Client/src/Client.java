import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client implements Runnable{
    private  BufferedReader reader;
    private BufferedWriter writer;
    ArrayList<Client>clients;
    String clientName;
    Client(Socket socket,ArrayList<Client>clients){
        try {
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            reader= new BufferedReader(isr);

            OutputStreamWriter o = new OutputStreamWriter(socket.getOutputStream());
            writer= new BufferedWriter(o);

            clientName=reader.readLine();
            this.clients=clients;
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try{
            String line=reader.readLine()+"\n";
            line=clientName+line;
            while(line!=null){
                for(Client client:clients){
                    synchronized (client.writer) {
                        client.writer.write(line);
                        client.writer.flush();
                    }
                }
                line=reader.readLine()+"\n";
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
