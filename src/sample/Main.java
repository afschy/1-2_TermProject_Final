package sample;

import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import networking.NetworkUtil;

public class Main extends Application{
    public static final String INPUT_FILE_NAME = "players.txt";
    public static final String OUTPUT_FILE_NAME = "players.txt";

    public static List<Player> playerList;
    public static List<Club> clubList;
    public static NetworkUtil client;
    public static Club currentClub;
    Stage stage;

    public static void main(String[] args){
        try{
            client = new NetworkUtil("127.0.0.1", 33333);
        }catch (Exception e){
            System.out.println("Client creation failed");
        }
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{
        playerList = sample.IOControl.readFromFile(INPUT_FILE_NAME);
        clubList = sample.Club.createClubList(playerList);

        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        stage.setTitle("Football Player Database System - Login");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}
