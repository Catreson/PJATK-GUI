package com.pjatk_gui;

import java.time.LocalDate;

import javafx.scene.image.Image;

public class Pracownik 
    extends Osoba{

        private double pensja;

        public Pracownik(String login, Password hPassword, String name, String surname, LocalDate bDate, Image profilePic, double pensja)
        throws LoginDuplicateException{
            super( login,  hPassword,  name,  surname,  bDate, profilePic);
            this.pensja = pensja;
            OsobaModel.getModel().save();
        }
        public Pracownik(String login, Password hPassword, String name, String surname, LocalDate bDate, double pensja)
            throws LoginDuplicateException{
                this(login, hPassword, name, surname, bDate, App.icon, pensja);
        }

        //SET and GET
        public double getPensja() {
            return pensja;
        }
        public void setPensja(double pensja) {
            this.pensja = pensja;
        }
}
