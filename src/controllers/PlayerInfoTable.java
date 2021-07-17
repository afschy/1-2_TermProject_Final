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
import javafx.scene.control.cell.PropertyValueFactory;
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
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        heightCol.setCellValueFactory(new PropertyValueFactory<>("height"));
        clubNameCol.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        jerseyNumberCol.setCellValueFactory(new PropertyValueFactory<>("jerseyNumber"));
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));

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
