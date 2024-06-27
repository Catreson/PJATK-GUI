package com.pjatk_gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class LoginView 
    extends BorderPane{
        private Button bLogin;
        private Button bCancel;
        private Label lLogin;
        private Label lPassword;
        private TextField fLogin;
        private PasswordField fPassword;
        private Label lCheck;
        @SuppressWarnings("unused")
        private Scene scene;
        private ImageView image;
        private Osoba user;
        
        public LoginView(){
            super();
            
            setBackground(new Background(new BackgroundFill(Color.rgb(0, 18, 32), null, null)));
            //setBackground(new Background(new BackgroundImage(new Image(App.class.getResource("gg1.jpg").toString(), 300, 200, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,  BackgroundPosition.CENTER, null)));
            image = new ImageView(new Image(App.class.getResource("gg1.jpg").toString(), 200, 200, true, false));
            scene = new Scene(this, 500, 200);
            //setStyle("-fx-base: rgba(0, 1, 2, 255);");
            bLogin = new Button("Login");
            bCancel = new Button("Cancel");
            lLogin = new Label("Login:");
            lPassword = new Label("Password:");
            fLogin = new TextField();
            fLogin.setPromptText("Your login");
            fPassword = new PasswordField();
            fPassword.setPromptText("Your password");
            lCheck = new Label();
            GridPane gP = new GridPane();
            gP.setPadding(new Insets(5.));
            GridPane.setColumnIndex(lLogin, 1);
            GridPane.setColumnIndex(fLogin, 2);
            GridPane.setColumnIndex(lPassword, 1);
            GridPane.setColumnIndex(fPassword, 2);
            GridPane.setColumnIndex(lCheck, 2);
            GridPane.setColumnSpan(lCheck, 2);
            GridPane.setColumnIndex(bLogin, 1);
            GridPane.setColumnIndex(bCancel, 2);
            GridPane.setRowIndex(lLogin, 1);
            GridPane.setRowIndex(fLogin, 1);
            GridPane.setRowIndex(lPassword, 2);
            GridPane.setRowIndex(fPassword, 2);
            GridPane.setRowIndex(lCheck, 3);
            GridPane.setRowIndex(bLogin, 4);
            GridPane.setRowIndex(bCancel, 4);
            gP.getChildren().addAll(lLogin,fLogin,lPassword,fPassword, lCheck, bLogin, bCancel);
            gP.setAlignment(Pos.CENTER);
            setLeft(image);
            setCenter(gP);

            bCancel.setOnAction(
                e -> {
                    getScene().getWindow().hide();
                    Platform.exit();
                }
            );

            bLogin.setOnAction(
                e -> {
                    boolean v = false;
                    try {
                        v = LoginManager.getLoginManager().verifyLogin(fLogin.getText(), fPassword.getText().toCharArray());
                    } catch (NoUserException ee) {
                        displayInvalid();
                    }
                    if(v){
                        user = OsobaModel.getModel().getByLogin(fLogin.getText());
                        getScene().getWindow().hide();
                    }
                    else{
                        displayInvalid();
                    }
                }
            );
        }

        public Osoba getOsoba(){
            return user;
        }

        private void displayInvalid(){
            lCheck.setText("Invalid username or password");
            lCheck.setTextFill(Color.TOMATO);
        }
}
