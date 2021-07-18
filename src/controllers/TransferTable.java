package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.Main;
import sample.Player;
import java.io.IOException;
import java.util.List;

public class TransferTable {
    @FXML
    private TableView tableViewOwn, tableViewTransfer;
    @FXML
    private TableColumn<Player, String> nameColOwn, countryColOwn, ageColOwn, heightColOwn, clubNameColOwn,
            positionColOwn, jerseyNumberColOwn, salaryColOwn;
    @FXML
    private TableColumn<Player, String> nameCol,countryCol,ageCol,heightCol,clubNameCol,positionCol,jerseyNumberCol,salaryCol;
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
        sample.UIUpdater.fillTableColumns(nameColOwn, countryColOwn, ageColOwn, heightColOwn, clubNameColOwn,
                positionColOwn, jerseyNumberColOwn, salaryColOwn);
        ObservableList<Player> data = FXCollections.observableArrayList(Main.currentClub.getPlayers());
        tableViewOwn.setItems(data);
    }

    public void loadTransfer(List<Player> transferList) {
        sample.UIUpdater.fillTableColumns(nameCol, countryCol, ageCol, heightCol, clubNameCol, positionCol, jerseyNumberCol, salaryCol);
        ObservableList<Player> data = FXCollections.observableArrayList(transferList);
        tableViewTransfer.setItems(data);
    }

    @FXML
    private void backButtonPressed() throws IOException {
        isRunning = false;
        Stage stage = (Stage)backButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        stage.setTitle("Football Player Database System - Home");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
