package com.pjatk_gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ProfileView 
    extends GridPane{
        private Label lLogin;
        private Label lName;
        private Label lSurname;
        private Label lBirthdate;
        private Label lValidTo;
        private Label lBalance;
        private Label lPensja;
        private Label lStyl;
        private Label lStatus;
        private Label lId;
        private TextField fName;
        private TextField fSurname;
        private DatePicker fBirthdate;
        private ImageView profilePic;
        private Button bEdit;
        private Button bAccept;

        public ProfileView(Osoba o){
            setAlignment(Pos.CENTER);
            setStyle("-fx-border-color: orange;\n" +
                   "-fx-border-insets: 10;\n" +
                   "-fx-hgap: 10px;\n" +
                   "-fx-vgap: 5px;\n" +
                   "-fx-padding: 5px;\n" +
                   "-fx-border-width: 1;\n" +
                   "-fx-border-radius: 5px;\n" +
                   "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);\n");

            lLogin = new Label("Login: ");
            lId = new Label("ID: ");
            lName = new Label("Imię: ");
            lSurname = new Label("Nazwisko: ");
            lBirthdate = new Label("Birthdate: ");
            lLogin = new Label("Login: ");
            lStyl = new Label("Styl zarządzania: ");
            lStatus = new Label("Rola użytkownika: ");
            lPensja = new Label("Pensja");
            lValidTo = new Label("Karnet ważny do: ");
            lBalance = new Label("Stan konta: ");
            fName = new TextField(o.getName());
            fSurname = new TextField(o.getSurname());
            fBirthdate = new DatePicker(o.getbDate());
            fName.setEditable(false);
            fSurname.setEditable(false);
            fBirthdate.setEditable(false);
            profilePic = new ImageView(o.getProfilePic());
            bEdit = new Button("Zmień dane");
            bEdit.setOnAction(
                e -> editMode()
            );
            bAccept = new Button("Akceptuj zmiany");
            bAccept.setOnAction(
                e -> acceptChanges(o)
            );

            GridPane.setColumnSpan(profilePic, 2);

            add(profilePic, 1, 0);
            add(lStatus, 1, 1);
            add(new Label(o.getClass().getSimpleName()), 2, 1);
            add(lLogin, 1, 2);
            add(new Label(o.getLogin()), 2, 2);
            add(lName, 1, 3);
            add(fName, 2, 3);
            add(lSurname, 1, 4);
            add(fSurname, 2, 4);
            add(lBirthdate, 1, 5);
            add(fBirthdate, 2, 5);
            add(lId, 1, 6);
            add(new Label(""+o.getiD()), 2, 6);
            add(bEdit, 1, 20);
            add(bAccept, 2, 20);

            if(o instanceof Klubowicz){
                Klubowicz k = (Klubowicz)o;
                add(lBalance, 1, 7);
                add(new Label("" + k.getAccountBalance()), 2, 7);
                add(lValidTo, 1, 8);
                add(new Label(k.getPassValitTo().toString()), 2, 8);
            }
            if(o instanceof Pracownik){
                Pracownik p = (Pracownik)o;
                add(lPensja, 1, 7);
                add(new Label("" + p.getPensja()), 2, 7);
            }
            if(o instanceof Manager){
                Manager m = (Manager)o;
                add(lStyl, 1, 8);
                add(new Label(m.getStylZarzadzania()), 2, 8);
                
            }

        }
        private void editMode(){
            fName.setEditable(true);
            fSurname.setEditable(true);
            fBirthdate.setEditable(true);
        }
        private void acceptChanges(Osoba o){
            fName.setEditable(false);
            fSurname.setEditable(false);
            fBirthdate.setEditable(false);
            o.setName(fName.getText());
            o.setSurname(fSurname.getText());
            o.setbDate(fBirthdate.getValue());
            OsobaModel.getModel().save();
        }
}
