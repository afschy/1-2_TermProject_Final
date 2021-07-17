package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Club;
import sample.Main;
import sample.Player;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;

public class SearchAmongClubs {
    @FXML
    private Button backButton, searchButton;
    @FXML
    private RadioButton radio1,radio2,radio3,radio4;
    @FXML
    private TextField nameField;
    private int radioStatus = 1;

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
    private void radioSelection(ActionEvent event){
        if(radio1.isSelected()) radioStatus = 1;
        if(radio2.isSelected()) radioStatus = 2;
        if(radio3.isSelected()) radioStatus = 3;
        if(radio4.isSelected()) radioStatus = 4;
    }

    @FXML
    private void searchButtonPressed(ActionEvent event) throws IOException {
        String name = sample.IOControl.formatName(nameField.getText());
        Club relevantClub = Main.currentClub;
        if(relevantClub == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR: Invalid Input");
            alert.setHeaderText("ERROR: Invalid Input");
            alert.setContentText("No such club exists.");
            alert.showAndWait();
            return;
        }
        List<Player> result;

        if(radioStatus <4){
            if(radioStatus == 1) result = sample.SearchClubs.maxSalaryPlayers(relevantClub);
            else if(radioStatus == 2) result = sample.SearchClubs.maxAgePlayers(relevantClub);
            else result = sample.SearchClubs.maxHeightPlayers(relevantClub);

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

        else{
            String text;
            NumberFormat fmt = NumberFormat.getInstance();
            fmt.setGroupingUsed(false);
            fmt.setMaximumIntegerDigits(999);
            fmt.setMaximumFractionDigits(2);
            text = fmt.format(sample.SearchClubs.yearlySalary(relevantClub));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Yearly Salary");
            alert.setHeaderText("Total yearly salary of all players in " + name);
            alert.setContentText(text);
            alert.showAndWait();
        }
    }
}
