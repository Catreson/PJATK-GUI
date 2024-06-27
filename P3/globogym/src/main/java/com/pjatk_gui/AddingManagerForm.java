package com.pjatk_gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class AddingManagerForm
    extends AddingPracownikForm{
        protected Label lStyl;
        protected TextField fStyl;
        public AddingManagerForm(){
            super();
            this.lTitle.setText("MANAGER");
            lTitle.setAlignment(Pos.CENTER);
            lStyl = new Label("Styl: "); 
            fStyl = new TextField();

            GridPane.setColumnIndex(lStyl, 1);
            GridPane.setColumnIndex(fStyl, 2);

            GridPane.setRowIndex(lStyl, 9);
            GridPane.setRowIndex(fStyl, 9);
            getChildren().addAll(lStyl, fStyl);
        }

        @Override
        public void add()
            throws InvalidUserException{
            if(!validate())
                throw new InvalidUserException("Missing parameters");
            try {
                    Password pwd = PasswordManager.getPasswordManager().createPassword(fPassword.getText().toCharArray(), 10000);
                    if (tmpImage != null)
                        new Manager(fLogin.getText(), pwd, fName.getText(), fSurname.getText(), fBirthdate.getValue(), tmpImage, Double.parseDouble(fSalary.getText()), fStyl.getText());
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
            if(fStyl.getText().length()<1){
                lStyl.setTextFill(Color.TOMATO);
                validated = false;
            }
            else{
                lStyl.setTextFill(Color.GREEN);
            }
            return validated;
        }

}
