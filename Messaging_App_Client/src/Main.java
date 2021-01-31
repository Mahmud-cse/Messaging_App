import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(5000);
            ArrayList<Client>clients=new ArrayList<>();
            while(true) {
                try {
                    Socket socket = server.accept();
                    Client client =new Client(socket,clients);
                    clients.add(client);
                    Thread clientT=new Thread(client);
                    clientT.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
