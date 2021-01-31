package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;

public class Controller {
    @FXML
    TextField msgTextField;
    @FXML
    Button sendButton;

    @FXML
    TextArea allTextArea;

    private BufferedReader reader;
    private  BufferedWriter writer;

    public Controller(BufferedReader reader, BufferedWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public Controller(){
        //String Name="Mahmud";
        try {
            Socket socket = new Socket("127.0.0.1", 5000);
            OutputStreamWriter o=new OutputStreamWriter(socket.getOutputStream());
            writer=new BufferedWriter(o);

            InputStream in;
            InputStreamReader isr=new InputStreamReader(socket.getInputStream());
            reader=new BufferedReader(isr);

            //writer.write(Name+"\n");
            //writer.flush();
            Thread t=new Thread(){
                public void run(){
                    try{
                        String line=reader.readLine()+"\n";
                        while(line!=null){
                            allTextArea.appendText(line);
                            line=reader.readLine()+"\n";
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void msgSend(){
        String Name="Mahmud:";
        try {
            String msg = msgTextField.getText() + "\n";
            msgTextField.setText("");
            writer.write(Name+msg+"\n");
            BufferedWriter bw =new BufferedWriter(new FileWriter("Files/file.txt"));
            bw.write("Mahmud"+msg+"\n");
            writer.flush();
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
