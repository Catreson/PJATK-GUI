module com.w14 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.w14 to javafx.fxml;
    exports com.w14;
}
