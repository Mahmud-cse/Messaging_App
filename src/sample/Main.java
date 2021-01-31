package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Messaging App(M)");
        primaryStage.setScene(new Scene(root, 485, 435));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
