package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.Player;
import java.io.IOException;
import java.util.List;

public class SearchAmongPlayers {
    @FXML
    private Button backButton, searchButton;
    @FXML
    private TextField nameField, countryField, posField, minField, maxField;

    @FXML
    private void backButtonPressed(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
        Parent root = loader.load();
        controllers.Home controller = loader.getController();
        controller.initTitle();
        stage.setTitle(Main.currentClub.getName());
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
        clubName = Main.currentClub.getName();
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

        List<Player> result = sample.SearchPlayers.masterPlayerSearch(Main.currentClub.getPlayers(),name,country,clubName,position,lowerBound,upperBound);

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
}
