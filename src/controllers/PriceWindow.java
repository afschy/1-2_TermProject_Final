package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Player;

public class PriceWindow {
    Player player;
    public void setPlayer(Player player){this.player = player;}

    @FXML
    TextField priceField;

    @FXML
    private void submitButtonPressed(){
        String text = priceField.getText();
        double price;
        try{
            price = Double.parseDouble(text);
            if(price < 0.0) throw new Exception();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Price must be a non-negative real number");
            alert.showAndWait();
            return;
        }
        player.setPrice(price);

        Stage currentStage = (Stage) priceField.getScene().getWindow();
        currentStage.close();
    }
}
