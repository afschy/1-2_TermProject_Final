package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import messages.TransferListRequest;
import sample.Main;

import java.io.IOException;
import java.util.TreeMap;

public class Home {
    @FXML
    private Button button1, button2, button3, button4, logOutButton, transferButton;
    @FXML
    private Label title;

    public void initTitle(){
        title.setText(Main.currentClub.getName());
        title.setAlignment(Pos.CENTER);
    }

    @FXML
    private void startSearchAmongPlayers() throws IOException {
        Stage stage = (Stage)button1.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/SearchAmongPlayers.fxml"));
        stage.setTitle("Search Players - " + Main.currentClub.getName());
        stage.setScene(new Scene(root));
        //stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void startSearchAmongClubs() throws IOException {
        Stage stage = (Stage)button2.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/SearchAmongClubs.fxml"));
        stage.setTitle("Search in Club - " + Main.currentClub.getName());
        stage.setScene(new Scene(root));
        //stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void startCountryWiseList() throws IOException {
        TreeMap<String, Integer> counter = sample.SearchPlayers.countryCount(Main.currentClub.getPlayers());
        Stage stage = (Stage)button3.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/CountrywiseTable.fxml"));
        Parent root = loader.load();

        CountrywiseTable controller = loader.getController();
        controller.load(counter);

        stage.setTitle("Country-wise Player Count - " + Main.currentClub.getName());
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void exitSystem() throws Exception {
        Stage stage = (Stage) button4.getScene().getWindow();
        //sample.IOControl.writeToFile(sample.Main.playerList, sample.Main.OUTPUT_FILE_NAME);
        Main.client.getNetworkUtil().closeConnection();
        //Main.client.getReadThread().setStatus(false);
        stage.close();
    }

    @FXML
    private void logOutButtonPressed() throws Exception{
        Stage stage = (Stage)logOutButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        stage.setTitle("Football Player Database System - Login");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void transferButtonPressed(){
        try{
            Main.client.getNetworkUtil().write(new TransferListRequest(sample.Main.currentClub.getName()));
            TransferTable.isRunning = true;
        }catch(Exception e){
            System.out.println("Transfer request sending failed");
            System.out.println(e);
        }
    }
}
