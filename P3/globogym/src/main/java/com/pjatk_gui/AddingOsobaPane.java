package com.pjatk_gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class AddingOsobaPane 
    extends GridPane{
        protected Button bAdd;
        protected Button bCancel;
        
        protected Image tmpImage;
        protected Label lTitle;
        protected Label lName;
        protected Label lSurname;
        protected Label lBirthdate;
        protected Label lLogin;
        protected Label lPassword;
        protected Label lProfilePic;
        protected ImageView iProfilePic;
        protected TextField fName;
        protected TextField fSurname;
        protected DatePicker fBirthdate;
        protected TextField fLogin;
        protected PasswordField fPassword;
        protected FileChooser fileChooser;
        protected String profilePicture = "";
        protected Button fProfilePic;

        public AddingOsobaPane(){
            super();

            bAdd= new Button("Dodaj");
            bCancel = new Button("Anuluj");
            bAdd.setOnAction(
                e -> {
                    try {
                        add();
                    } catch (InvalidUserException e1) {
                        System.out.println(e);
                    }
                }
            );

            bCancel.setOnAction(
                e -> getScene().getWindow().hide()
            );


            lTitle = new Label();
            lName = new Label("Name");
            lSurname = new Label("Surname");
            lBirthdate = new Label("Birthdate");
            lLogin = new Label("Login");
            lPassword = new Label("Password");
            lProfilePic = new Label("Zdjęcie profilowe");
            iProfilePic = new ImageView(App.icon);
            fName = new TextField();
            fSurname = new TextField();
            fBirthdate = new DatePicker();
            fLogin = new TextField();
            fPassword = new PasswordField();
            fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            fProfilePic = new Button("Select picture");
            fProfilePic.setOnAction(
                e -> {
                    File file = fileChooser.showOpenDialog(getScene().getWindow());
                    profilePicture = file.getAbsolutePath();
                    if(profilePicture != null){
                        try(FileInputStream fIS = new FileInputStream(profilePicture)) {
                            tmpImage = new Image(fIS, 50, 50, true, false);
                            iProfilePic.setImage(tmpImage);
                        } catch (FileNotFoundException e1) {
                            System.out.println(e1);
                        }
                        catch (Exception e1) {
                            System.out.println(e1);
                        }
                    }
                }
            );

            GridPane.setColumnIndex(lTitle, 1);
            GridPane.setColumnSpan(lTitle, 2);
            GridPane.setColumnIndex(lName, 1);
            GridPane.setColumnIndex(lSurname, 1);
            GridPane.setColumnIndex(lBirthdate, 1);
            GridPane.setColumnIndex(lLogin, 1);
            GridPane.setColumnIndex(lPassword, 1);
            GridPane.setColumnIndex(lProfilePic, 1);
            GridPane.setColumnIndex(iProfilePic, 3);

            GridPane.setColumnIndex(fName, 2);
            GridPane.setColumnIndex(fSurname, 2);
            GridPane.setColumnIndex(fBirthdate, 2);
            GridPane.setColumnIndex(fLogin, 2);
            GridPane.setColumnIndex(fPassword, 2);
            GridPane.setColumnIndex(fProfilePic, 2);

            GridPane.setColumnIndex(bAdd, 1);
            GridPane.setColumnIndex(bCancel, 2);

            GridPane.setRowIndex(lTitle, 1);
            GridPane.setRowIndex(iProfilePic, 2);
            GridPane.setRowSpan(iProfilePic, 4);
            GridPane.setRowIndex(lName, 2);
            GridPane.setRowIndex(lSurname, 3);
            GridPane.setRowIndex(lBirthdate, 4);
            GridPane.setRowIndex(lLogin, 5);
            GridPane.setRowIndex(lPassword, 6);
            GridPane.setRowIndex(lProfilePic, 7);

            GridPane.setRowIndex(fName, 2);
            GridPane.setRowIndex(fSurname, 3);
            GridPane.setRowIndex(fBirthdate, 4);
            GridPane.setRowIndex(fLogin, 5);
            GridPane.setRowIndex(fPassword, 6);
            GridPane.setRowIndex(fProfilePic, 7);

            GridPane.setRowIndex(bAdd, 37);
            GridPane.setRowIndex(bCancel, 37);


            getChildren().addAll(lTitle,lName,lSurname,lBirthdate,lLogin,lPassword, fName, fSurname, fBirthdate, fLogin, fPassword, bAdd, bCancel, lProfilePic, fProfilePic, iProfilePic);
        }

        protected void add()
            throws InvalidUserException{
            if(!validate())
                return;
        }

        protected boolean validate(){
            boolean pass = true;
            if (fName.getText().length() < 1){
                lName.setTextFill(Color.TOMATO);
                pass = false;
            }
            else
                lName.setTextFill(Color.GREEN);
            if (fSurname.getText().length() < 1){
                lSurname.setTextFill(Color.TOMATO);
                pass = false;
            }
            else
                lSurname.setTextFill(Color.GREEN);
            if (fBirthdate.getValue() == null){
                lBirthdate.setTextFill(Color.TOMATO);
                pass = false;
            }
            else
                lBirthdate.setTextFill(Color.GREEN);
            if (fLogin.getText().length() < 1){
                lLogin.setTextFill(Color.TOMATO);
                pass = false;
            }
            else
                lLogin.setTextFill(Color.GREEN);
            if (fPassword.getText().length() < 1){
                lPassword.setTextFill(Color.TOMATO);
                pass = false;
            }
            else
                lPassword.setTextFill(Color.GREEN);
            if (profilePicture.length() < 1){
                    lProfilePic.setTextFill(Color.TOMATO);
                    pass = false;
            }
            else
                    lProfilePic.setTextFill(Color.GREEN);
            return pass;
            
        }
}
class InvalidUserException
    extends Exception{
        public InvalidUserException(String msg){
            super(msg);
        }
    }