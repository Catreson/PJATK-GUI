module com.pjatk_gui {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.pjatk_gui to javafx.fxml;
    exports com.pjatk_gui;
}
