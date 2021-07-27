package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import messages.BuyRequest;
import messages.SellCancelRequest;
import messages.SellRequest;
import sample.Main;
import sample.Player;
import sample.PlayerWithImage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransferTable {
    @FXML
    private TableView<PlayerWithImage> tableViewOwn, tableViewTransfer;
    @FXML
    private TableColumn<PlayerWithImage, String> nameColOwn, countryColOwn, ageColOwn, heightColOwn, clubNameColOwn,
            positionColOwn, jerseyNumberColOwn, salaryColOwn;
    @FXML
    private TableColumn<PlayerWithImage, ImageView> imageViewColOwn;
    @FXML
    private TableColumn<PlayerWithImage, String> nameCol,countryCol,ageCol,heightCol,clubNameCol,positionCol,
            jerseyNumberCol,salaryCol, priceCol;
    @FXML
    private TableColumn<PlayerWithImage, ImageView> imageViewCol;
    @FXML
    private Button backButton, sellButton, buyButton;
    @FXML
    private Label header;
    public static boolean isRunning = false;

    public void initialize(List<Player> transferList){
        isRunning = true;
        header.setText("Players in " + Main.currentClub.getName() + ":");
        loadOwn();
        loadTransfer(transferList);
    }

    public void loadOwn() {
        List<PlayerWithImage> list = new ArrayList<>();
        for(Player p: Main.currentClub.getPlayers()){
            PlayerWithImage playerWithImage = new PlayerWithImage(p);
            playerWithImage.initImage();
            list.add(playerWithImage);
        }

        sample.UIUpdater.fillTableColumns(nameColOwn, countryColOwn, ageColOwn, heightColOwn, clubNameColOwn,
                positionColOwn, jerseyNumberColOwn, salaryColOwn, imageViewColOwn);
        ObservableList<PlayerWithImage> data = FXCollections.observableArrayList(list);
        tableViewOwn.setItems(data);
    }

    public void loadTransfer(List<Player> transferList) {
        List<PlayerWithImage> list = new ArrayList<>();
        for(Player p: transferList){
            PlayerWithImage playerWithImage = new PlayerWithImage(p);
            playerWithImage.initImage();
            list.add(playerWithImage);
        }

        sample.UIUpdater.fillTableColumns(nameCol, countryCol, ageCol, heightCol, clubNameCol, positionCol,
                jerseyNumberCol, salaryCol, imageViewCol);
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        ObservableList<PlayerWithImage> data = FXCollections.observableArrayList(list);
        tableViewTransfer.setItems(data);
    }

    @FXML
    private void backButtonPressed() throws IOException {
        isRunning = false;
        Stage stage = (Stage)backButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
        Parent root = loader.load();
        controllers.Home controller = loader.getController();
        controller.initTitle();
        stage.setTitle(Main.currentClub.getName());
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void sellButtonPressed() throws Exception{
        Player selectedPlayer;
        try{
            selectedPlayer = new Player(tableViewOwn.getSelectionModel().getSelectedItem());
        }catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR: Sell Request Failed");
            alert.setHeaderText("No Player Selected");
            alert.showAndWait();
            return;
        }

        Stage priceStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/PriceWindow.fxml"));
        Parent root = loader.load();
        PriceWindow controller = loader.getController();
        controller.setPlayer(selectedPlayer);
        priceStage.setTitle("Price Getter");
        priceStage.setScene(new Scene(root));
        priceStage.showAndWait();

        try{
            Main.client.getNetworkUtil().write(new SellRequest(new Player(selectedPlayer)));
        }catch(Exception e){
            System.out.println("Sell request sending failed");
            System.out.println(e);
        }
    }

    @FXML
    private void buyButtonPressed(){
        Player selectedPlayer;
        try{
            selectedPlayer = new Player(tableViewTransfer.getSelectionModel().getSelectedItem());
        }catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR: Buy Request Failed");
            alert.setHeaderText("No Player Selected");
            alert.showAndWait();
            return;
        }

        if(selectedPlayer.getClubName().equalsIgnoreCase(Main.currentClub.getName())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR: Buy Request Failed");
            alert.setHeaderText("Cannot Buy Own Player");
            alert.showAndWait();
        }
        else{
            try{
                Main.client.getNetworkUtil().write(new BuyRequest(new Player(selectedPlayer), Main.currentClub.getName()));
            }catch(Exception e){
                System.out.println("Buy request sending failed");
                System.out.println(e);
            }
        }
    }

    @FXML
    private void cancelButtonPressed(){
        Player selectedPlayer;
        try{
            selectedPlayer = new Player(tableViewTransfer.getSelectionModel().getSelectedItem());
        }catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No Player Selected");
            alert.showAndWait();
            return;
        }

        if(!selectedPlayer.getClubName().equalsIgnoreCase(Main.currentClub.getName())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Can only remove own players from transfer list");
            alert.showAndWait();
            return;
        }

        try{
            Main.client.getNetworkUtil().write(new SellCancelRequest(selectedPlayer));
        }catch(Exception e){
            System.out.println("Removal message sending failed");
            System.out.println(e);
        }
    }
}
