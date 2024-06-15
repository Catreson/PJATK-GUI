package com.example;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        HBox root = new HBox();
        //VBox root = new VBox();
        // root = new StackPane();
        //BorderPane root = new BorderPane();
        /*for(int i=0; i < 10; i++){
        root.getChildren().add(new Rectangle(Math.random() * 100, Math.random()*100, 
            new Color(Math.random(), Math.random(), Math.random(), Math.random())));
        }*/
        /*Rectangle r1 = new Rectangle(30, 30, Color.FUCHSIA);
        Rectangle r2 = new Rectangle(70, 70, Color.YELLOWGREEN);
        Rectangle r3 = new Rectangle(90, 90, Color.STEELBLUE);
        Rectangle r4 = new Rectangle(90, 90, Color.TOMATO);
        Rectangle r5 = new Rectangle(90, 90, Color.TURQUOISE);
        //root.getChildren().add(r1);
        //root.getChildren().add(r2);
        //root.getChildren().add(r3);
        root.setCenter(r5);
        root.setTop(r1);
        root.setBottom(r2);
        root.setLeft(r4);
        root.setRight(r3);

        BorderPane.setAlignment(r1, Pos.CENTER);
        BorderPane.setAlignment(r2, Pos.CENTER);
        BorderPane.setAlignment(r3, Pos.CENTER);
        BorderPane.setAlignment(r4, Pos.CENTER);
        */

        Label fLabel = new Label("Japko");
        TextField fText = new TextField();

        Button fButton = new Button("Click");

        root.getChildren().addAll(fLabel, fText, fButton);
        fButton.setOnAction(
            e -> System.out.println(fText.getText() + "tu")
        );

        scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}