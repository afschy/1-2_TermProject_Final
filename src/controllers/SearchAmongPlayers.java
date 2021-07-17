package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.Player;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

public class SearchAmongPlayers {
    @FXML
    private Button backButton, searchButton, countrywiseButton;
    @FXML
    private TextField nameField, countryField, clubField, posField, minField, maxField;

    @FXML
    private void backButtonPressed(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        stage.setTitle("Football Player Database System - Home");
        stage.setScene(new Scene(root));
        //stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void searchButtonPressed(ActionEvent event) throws IOException{
        String name, country, clubName, position;
        double lowerBound = 0, upperBound = 0;

        name = sample.IOControl.formatName(nameField.getText());
        country = sample.IOControl.formatName(countryField.getText());
        clubName = sample.IOControl.formatName(clubField.getText());
        position = sample.IOControl.formatName(posField.getText());

        try {
            lowerBound = Double.parseDouble(minField.getText().trim());
            upperBound = Double.parseDouble(maxField.getText().trim());
            if(lowerBound < 0 || upperBound < 0) throw new Exception();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR: Invalid Input");
            alert.setHeaderText("ERROR: Invalid Input");
            alert.setContentText("Salary must be a non-negative real number");
            alert.showAndWait();
            return;
        }

        List<Player> result = sample.SearchPlayers.masterPlayerSearch(Main.playerList,name,country,clubName,position,lowerBound,upperBound);

        Stage stage = (Stage)searchButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/PlayerInfoTable.fxml"));
        Parent root = loader.load();

        PlayerInfoTable controller = loader.getController();
        controller.load(result);

        stage.setTitle("Search Results");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void countrywiseButtonPressed(ActionEvent event) throws IOException{
        TreeMap<String, Integer> counter = sample.SearchPlayers.countryCount(Main.playerList);
        Stage stage = (Stage)countrywiseButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/CountrywiseTable.fxml"));
        Parent root = loader.load();

        CountrywiseTable controller = loader.getController();
        controller.load(counter);

        stage.setTitle("Country-wise Player Count");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
