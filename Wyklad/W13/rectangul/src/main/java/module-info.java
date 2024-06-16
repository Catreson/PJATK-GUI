module com.w13 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.w13 to javafx.fxml;
    exports com.w13;
}
