module com.w15 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.w15 to javafx.fxml;
    exports com.w15;
}
