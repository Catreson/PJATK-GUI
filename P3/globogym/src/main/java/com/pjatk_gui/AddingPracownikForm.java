package com.pjatk_gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class AddingPracownikForm
    extends AddingOsobaForm{
        protected Label lSalary;
        protected TextField fSalary;
        public AddingPracownikForm(){
            super();
            this.lTitle.setText("PRACOWNIK");
            lTitle.setAlignment(Pos.CENTER);
            lSalary = new Label("Pensja: "); 
            fSalary = new TextField();
            fSalary.textProperty().addListener(new ChangeListener<String>() {
            @Override
             public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    fSalary.setText(oldValue);
                }
            }
            });

            GridPane.setColumnIndex(lSalary, 1);
            GridPane.setColumnIndex(fSalary, 2);

            GridPane.setRowIndex(lSalary, 8);
            GridPane.setRowIndex(fSalary, 8);
            getChildren().addAll(lSalary, fSalary);
        }

        @Override
        public void add()
            throws InvalidUserException{
            if(!validate())
                throw new InvalidUserException("Missing parameters");
            try {
                    Password pwd = PasswordManager.getPasswordManager().createPassword(fPassword.getText().toCharArray(), 10000);
                    if (tmpImage != null)
                        new Pracownik(fLogin.getText(), pwd, fName.getText(), fSurname.getText(), fBirthdate.getValue(), tmpImage, Double.parseDouble(fSalary.getText()));
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
            if(Double.parseDouble(fSalary.getText()) < 0){
                validated = false;
                lSalary.setTextFill(Color.TOMATO);
            }
            else{
                lSalary.setTextFill(Color.GREEN);
            }
            return validated;
        }

}
