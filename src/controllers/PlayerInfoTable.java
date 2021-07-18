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
import sample.Player;

import java.io.IOException;
import java.util.List;

public class PlayerInfoTable {
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<Player, String> nameCol,countryCol,ageCol,heightCol,clubNameCol,positionCol,jerseyNumberCol,salaryCol;
    @FXML
    private Label counter;
    @FXML
    private Button backButton;

    public void load(List<Player> searchResult) {
        sample.UIUpdater.fillTableColumns(nameCol, countryCol, ageCol, heightCol, clubNameCol, positionCol, jerseyNumberCol, salaryCol);
        ObservableList<Player> data = FXCollections.observableArrayList(searchResult);
        tableView.setItems(data);
        counter.setText(searchResult.size() + " player(s) found");
    }

    @FXML
    private void backButtonPressed() throws IOException {
        Stage stage = (Stage)backButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        stage.setTitle("Football Player Database System - Home");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
