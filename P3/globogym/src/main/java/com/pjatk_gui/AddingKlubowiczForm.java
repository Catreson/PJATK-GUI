package com.pjatk_gui;

import java.time.LocalDate;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class AddingKlubowiczForm
    extends AddingOsobaForm{
        protected Label lValidTo;
        protected Label fValidTo;
        LocalDate tomorrowDate;
        public AddingKlubowiczForm(){
            super();
            this.lTitle.setText("KLUBOWICZ");
            lTitle.setAlignment(Pos.CENTER);
            lValidTo = new Label("Ważny do: "); 
            tomorrowDate = LocalDate.now().plusDays(1);
            fValidTo = new Label(tomorrowDate.toString());

            GridPane.setColumnIndex(lValidTo, 1);
            GridPane.setColumnIndex(fValidTo, 2);

            GridPane.setRowIndex(lValidTo, 8);
            GridPane.setRowIndex(fValidTo, 8);
            getChildren().addAll(lValidTo, fValidTo);
        }

        @Override
        public void add()
            throws InvalidUserException{
            if(!validate())
                throw new InvalidUserException("Missing parameters");
            try {
                    Password pwd = PasswordManager.getPasswordManager().createPassword(fPassword.getText().toCharArray(), 10000);
                    if (tmpImage != null)
                        new Klubowicz(fLogin.getText(), pwd, fName.getText(), fSurname.getText(), fBirthdate.getValue(), tmpImage, tomorrowDate, 0.);
            } catch (PasswordTooShortException e) {
                    throw new InvalidUserException("Password to short");
            } catch (LoginDuplicateException e){
                throw new InvalidUserException("Login duplicate");
            }
            System.out.println("ADDED");
            
                
        }

        @Override
        public boolean validate(){
            boolean validated = true;
            validated = super.validate();
            
            return validated;
        }

}
