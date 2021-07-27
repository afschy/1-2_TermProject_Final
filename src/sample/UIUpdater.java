package sample;

import controllers.TransferTable;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.List;

public class UIUpdater {
    Stage stage;

    public UIUpdater(Stage stage) {
        this.stage = stage;
    }

    public void login(Club club) {
        Platform.runLater(() -> {
            Main.currentClub = club;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));

            Parent root;
            try {
                root = loader.load();
            } catch (Exception e) {
                System.out.println(e);
                return;
            }

            controllers.Home controller = loader.getController();
            controller.initTitle();
            stage.setTitle(Main.currentClub.getName());
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        });
    }

    public void loginFailed() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR: Login Failed");
            alert.setHeaderText("ERROR: Login Failed");
            alert.showAndWait();
        });
    }

    public void initTransferList(List<Player> receivedList, Club receivedClub){
        Platform.runLater(()->{
            Main.currentClub = receivedClub;
            //Main.transferList = receivedList;
            if(TransferTable.isRunning){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/TransferTable.fxml"));
                Parent root;
                try{
                    root = loader.load();
                }catch (Exception e){
                    System.out.println(e);
                    return;
                }

                TransferTable controller = loader.getController();
                controller.initialize(receivedList);

                stage.setTitle("Transfer Window - " + Main.currentClub.getName());
                stage.setScene(new Scene(root));
                stage.setResizable(true);
                stage.show();
            }
        });
    }

    public static void fillTableColumns(TableColumn<PlayerWithImage, String> nameCol, TableColumn<PlayerWithImage, String> countryCol,
                                        TableColumn<PlayerWithImage, String> ageCol, TableColumn<PlayerWithImage, String> heightCol,
                                        TableColumn<PlayerWithImage, String> clubNameCol, TableColumn<PlayerWithImage, String> positionCol,
                                        TableColumn<PlayerWithImage, String> jerseyNumberCol, TableColumn<PlayerWithImage, String> salaryCol,
                                        TableColumn<PlayerWithImage, ImageView> imageViewCol) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        heightCol.setCellValueFactory(new PropertyValueFactory<>("height"));
        clubNameCol.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        jerseyNumberCol.setCellValueFactory(new PropertyValueFactory<>("jerseyNumber"));
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));
        imageViewCol.setCellValueFactory(new PropertyValueFactory<>("imageView"));
        imageViewCol.setPrefWidth(120);
    }
}