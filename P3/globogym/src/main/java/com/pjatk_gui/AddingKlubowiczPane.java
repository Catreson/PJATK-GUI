package com.pjatk_gui;

import javafx.geometry.Pos;

public class AddingKlubowiczPane
    extends AddingOsobaPane{

        public AddingKlubowiczPane(){
            super();
            this.lTitle.setText("KLUBOWICZ");
            lTitle.setAlignment(Pos.CENTER);
        }

        @Override
        public void add()
            throws InvalidUserException{
            if(!validate())
                return;
            try {
                    Password pwd = PasswordManager.getPasswordManager().createPassword(fPassword.getText().toCharArray(), 10000);
                    if (tmpImage != null)
                        new Klubowicz(fLogin.getText(), pwd, fName.getText(), fSurname.getText(), fBirthdate.getValue(), tmpImage);
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
