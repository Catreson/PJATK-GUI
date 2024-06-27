module com.pjatk_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires transitive javafx.swing;

    opens com.pjatk_gui to javafx.fxml;
    exports com.pjatk_gui;
}
