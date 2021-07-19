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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Main;
import sample.Player;
import sample.PlayerWithImage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerInfoTable {
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<PlayerWithImage, String> nameCol,countryCol,ageCol,heightCol,clubNameCol,positionCol,jerseyNumberCol,salaryCol;
    @FXML
    private TableColumn<PlayerWithImage, ImageView> imageViewCol;
    @FXML
    private Label counter;
    @FXML
    private Button backButton;

    public void load(List<Player> searchResult) {
        List<PlayerWithImage> list = new ArrayList<>();
        for(Player p: searchResult){
            PlayerWithImage playerWithImage = new PlayerWithImage(p);
            playerWithImage.initImage();
            list.add(playerWithImage);
        }

        sample.UIUpdater.fillTableColumns(nameCol, countryCol, ageCol, heightCol, clubNameCol, positionCol,
                jerseyNumberCol, salaryCol, imageViewCol);
        ObservableList<PlayerWithImage> data = FXCollections.observableArrayList(list);
        tableView.setItems(data);
        counter.setText(searchResult.size() + " player(s) found");
    }

    @FXML
    private void backButtonPressed() throws IOException {
        Stage stage = (Stage)backButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        stage.setTitle(Main.currentClub.getName());
        stage.setScene(new Scene(root));
        stage.show();
    }
}
