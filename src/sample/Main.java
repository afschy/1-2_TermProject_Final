package sample;

import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import networking.Client;

public class Main extends Application{
    public static final String INPUT_FILE_NAME = "players.txt";
    public static final String OUTPUT_FILE_NAME = "players.txt";

    public static List<Player> playerList;
    //public static List<Club> clubList;
    public static networking.Client client;
    public static Club currentClub;
    Stage stage;

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{
        try{
            client = new Client("127.0.0.1", 33333, this);
        }catch (Exception e){
            System.out.println("Client creation failed");
        }

        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        stage.setTitle("Football Player Database System - Login");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void login(Club club){
        Platform.runLater(()-> {
            //System.out.println("Running");
            currentClub = club;
            playerList = club.getPlayers();

            Parent root = null;
            try{
                root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
            }catch (Exception e){
                System.out.println(e);
            }
            stage.setTitle("Football Player Database System - Home");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        });
    }
    public void loginFailed(){
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR: Login Failed");
            alert.setHeaderText("ERROR: Login Failed");
            alert.showAndWait();
        });
    }
}
