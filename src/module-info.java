module myjfx {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    opens sample to javafx.graphics, javafx.fxml, javafx.base;
    opens fxml to javafx.graphics, javafx.fxml, javafx.base;
    opens controllers to javafx.graphics, javafx.fxml, javafx.base;
}