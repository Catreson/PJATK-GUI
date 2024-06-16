package com.pjatk_gui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginScene 
    extends Scene{
        private Button bLogin;
        private Button bCancel;
        private Label lLogin;
        private Label lPassword;
        private TextField fLogin;
        private PasswordField fPassword;
        
        public LoginScene(Parent root){
            super(root);
            bLogin = new Button("Login");
            bCancel = new Button("Cancel");
            lLogin = new Label("Login:");
            lPassword = new Label("Password:");
            fLogin = new TextField();
            fLogin.setPromptText("Your login");
            fPassword = new PasswordField();
            fPassword.setPromptText("Your password");
            
        }
}
