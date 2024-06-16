package com.pjatk_gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App 
    extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Scene scene = new Scene(root);
        Stage login = new Stage();
        LoginScene loginScene = new LoginScene(root);
        stage.setScene(loginScene);
        stage.show();
        login.showAndWait();
        System.out.println("e");
    }


    public static void main(String[] args) {
        launch();
    }

}