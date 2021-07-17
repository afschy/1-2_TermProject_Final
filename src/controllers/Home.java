package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Home {
    @FXML
    private Button button1, button2, button3, button4, logOutButton;

    @FXML
    private void startSearchAmongPlayers() throws IOException {
        Stage stage = (Stage)button1.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/SearchAmongPlayers.fxml"));
        stage.setTitle("Football Player Database System - Search Among Players");
        stage.setScene(new Scene(root));
        //stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void startSearchAmongClubs() throws IOException {
        Stage stage = (Stage)button2.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/SearchAmongClubs.fxml"));
        stage.setTitle("Football Player Database System - Search Among Clubs");
        stage.setScene(new Scene(root));
        //stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void startAddNewPlayer() throws IOException {
        Stage stage = (Stage)button3.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddNewPlayer.fxml"));
        stage.setTitle("Football Player Database System - Add New Player");
        stage.setScene(new Scene(root));
        //stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void exitSystem() throws Exception {
        Stage stage = (Stage) button4.getScene().getWindow();
        //sample.IOControl.writeToFile(sample.Main.playerList, sample.Main.OUTPUT_FILE_NAME);
        stage.close();
    }

    @FXML
    private void logOutButtonPressed()throws Exception{
        Stage stage = (Stage)logOutButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        stage.setTitle("Football Player Database System - Login");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}
