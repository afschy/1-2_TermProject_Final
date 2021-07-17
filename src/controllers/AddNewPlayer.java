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
import sample.Club;
import sample.Main;
import sample.Player;
import java.io.IOException;
import sample.AddPlayer;

import static sample.Main.playerList;

public class AddNewPlayer {
    @FXML
    private Button backButton, submitButton;
    @FXML
    private TextField nameField, countryField, ageField, heightField, clubNameField, positionField, jerseyNumberField, salaryField;

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
    private void submitButtonPressed(ActionEvent event){
        String name, country, clubName, position;
        int age, jerseyNumber;
        double height, salary;
        Player playerToAdd = new Player();

        name = sample.IOControl.formatName(nameField.getText());
        if(!AddPlayer.verifyName(playerList, name)){
            showAlert("A player with this name already exists");
            return;
        }
        if(!AddPlayer.verifyCharacters(name)){
            showAlert("Blank or Invalid characters in Name");
            return;
        }
        playerToAdd.setName(name);

        country = sample.IOControl.formatName(countryField.getText());
        if(!AddPlayer.verifyCharacters(country)){
            showAlert("Blank or Invalid characters in Country");
            return;
        }
        playerToAdd.setCountry(country);

        try{
            age = Integer.parseInt(ageField.getText());
            if(age<=0) throw new Exception();
        } catch (Exception e) {
            showAlert("Age must be a positive integer");
            return;
        }
        playerToAdd.setAge(age);

        try{
            height = Double.parseDouble(heightField.getText());
            if(height <= 0) throw new Exception();
        }catch(Exception e){
            showAlert("Height must be a positive real number");
            return;
        }
        playerToAdd.setHeight(height);

        clubName = sample.IOControl.formatName(clubNameField.getText());
        if(!AddPlayer.verifyCharacters(clubName)){
            showAlert("Blank or Invalid characters in Club");
            return;
        }
        playerToAdd.setClubName(clubName);

        position = sample.IOControl.formatName(positionField.getText());
        if(!AddPlayer.verifyPosition(position)){
            showAlert("Position must be Goalkeeper, Defender, Midfielder, or Forward");
            return;
        }
        playerToAdd.setPosition(position);

        try{
            jerseyNumber = Integer.parseInt(jerseyNumberField.getText());
            if(jerseyNumber<=0) throw new Exception();
        } catch (Exception e) {
            showAlert("Number must be a positive integer");
            return;
        }
        if(!AddPlayer.verifyNumber(playerToAdd.getClubName(), jerseyNumber, playerList)){
            showAlert("A player with this number already exists in " + playerToAdd.getClubName());
            return;
        }
        playerToAdd.setJerseyNumber(jerseyNumber);

        try{
            salary = Double.parseDouble(salaryField.getText());
            if(salary <= 0) throw new Exception();
        }catch(Exception e){
            showAlert("Salary must be a positive real number");
            return;
        }
        playerToAdd.setSalary(salary);

        Main.currentClub.getPlayers().add(playerToAdd);
        playerList.add(playerToAdd);
    }

    private void showAlert(String body){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR: Invalid Input");
        alert.setHeaderText("ERROR: Invalid Input");
        alert.setContentText(body);
        alert.showAndWait();
    }
}
