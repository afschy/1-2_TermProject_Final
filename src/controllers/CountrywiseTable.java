package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CountrywiseTable {

    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<Country, String> nameCol, playerCountCol;
    @FXML
    private Label label1, label2;

    public void load(TreeMap<String, Integer> counter) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        playerCountCol.setCellValueFactory(new PropertyValueFactory<>("playerCount"));

        int playerCount = 0;
        List<Country> result = new ArrayList<>();
        for(Map.Entry<String, Integer> hash : counter.entrySet()) {
            result.add(new Country(hash.getKey(), hash.getValue()));
            playerCount += hash.getValue();
        }

        ObservableList<Country> data = FXCollections.observableArrayList(result);
        tableView.setItems(data);

        label1.setText("Total Number of Countries: " + result.size());
        label2.setText("Total Number of Players: " + playerCount);
    }
}
