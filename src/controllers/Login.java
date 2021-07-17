package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import messages.LoginRequest;

import static sample.Main.client;

public class Login {
    @FXML
    private Button logInButton;
    @FXML
    private TextField clubNameField;

    @FXML
    private void logInButtonPressed() throws Exception{
        String name = sample.IOControl.formatName(clubNameField.getText());
        LoginRequest loginRequest = new LoginRequest(name);
        try{
            client.getNetworkUtil().write(loginRequest);
            System.out.println("Login Request sent");
        }catch (Exception e){
            System.out.println("Login request sending failed");
            System.out.println(e);
        }
    }
}
