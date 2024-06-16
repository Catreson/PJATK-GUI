package com.w13;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private BooleanProperty ACCES_GRANTED = new SimpleBooleanProperty();
    private final int MAX_ATTEMPTS = 3;
    private IntegerProperty ATTEMPT = new SimpleIntegerProperty();

    @Override
    public void start(Stage stage) throws IOException {
        User user = new User("Andrzej","123");
        Group root = new Group();
        Scene scene = new Scene( root, 320, 240);

        Rectangle background = new Rectangle(100, 100);
        background.setFill(Color.DEEPSKYBLUE);

        background.widthProperty().bind(
            scene.widthProperty()
        );

        background.heightProperty().bind(
            scene.heightProperty()
        );

        Label lUserNAme = new Label("no name");
        PasswordField fPasswordField = new PasswordField();

        FlowPane fP = new FlowPane();
        fP.getChildren().addAll(lUserNAme, fPasswordField);

        lUserNAme.textProperty().bind(
            user.userNameProperty()
        );

        fPasswordField.textProperty().addListener(
            (obs, ov, nv) -> ACCES_GRANTED.set(user.passwordProperty().getValue().equals(nv))
        );
        
        /*ACCES_GRANTED.addListener(
            (obs, ov, nv) -> background.setFill(
                ACCES_GRANTED.get() ? 
                Color.GREEN :
                Color.TOMATO
            )
        );

        fPasswordField.setOnAction(
            e -> {
                if(ACCES_GRANTED.get())
                    background.setFill(Color.GREEN);
                else
                    {
                        background.setFill(Color.TOMATO);
                        ATTEMPT.set(ATTEMPT.add(1).getValue());
                    }
            }
        );
        ATTEMPT.addListener(
            (obs, ov, nv) -> {
                if(nv.intValue() >= MAX_ATTEMPTS)
                    Platform.exit();
            }
        );*/

        root.getChildren().addAll(background, fP);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

}