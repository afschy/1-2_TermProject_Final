package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static sample.Main.client;
import static sample.Main.currentClub;

public class Login {
    @FXML
    private Button logInButton;
    @FXML
    private TextField clubNameField;

    @FXML
    private void logInButtonPressed() throws Exception{
        String name = sample.IOControl.formatName(clubNameField.getText());
        try{
            client.write(name);
        }catch (Exception e){
            System.out.println("Write failed");
            System.out.println(e);
        }
        Object o = null;
        try{
            o = client.read();
        }catch(Exception e){
            System.out.println("Read failed");
            System.out.println(e);
        }

        if(o instanceof sample.Club){
            sample.Main.currentClub = (sample.Club)o;
            sample.Main.playerList = currentClub.getPlayers();
            Stage stage = (Stage)logInButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
            stage.setTitle("Football Player Database System - Home");
            stage.setScene(new Scene(root));
            stage.show();
        }

        else if(o instanceof String){
            String message = (String)o;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR: Invalid Input");
            alert.setHeaderText(message);
            alert.setContentText("");
            alert.showAndWait();
        }
    }
}
