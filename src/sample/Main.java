package sample;

import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import networking.Client;

public class Main extends Application{
    public static Client client;
    public static Club currentClub;
    public static List<Player> transferList;
    Stage stage;

    public static void main(String[] args){ launch(args); }

    public void start(Stage primaryStage) throws Exception{
        try{
            client = new Client("127.0.0.1", 33333, new UIUpdater(primaryStage));
        }catch (Exception e){
            System.out.println("Client creation failed");
            System.out.println(e);
        }

        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        stage.setTitle("Football Player Database System - Login");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}
