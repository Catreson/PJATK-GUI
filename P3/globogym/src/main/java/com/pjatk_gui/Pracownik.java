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
        }
        public Pracownik(String login, Password hPassword, String name, String surname, LocalDate bDate, double pensja)
            throws LoginDuplicateException{
            super( login,  hPassword,  name,  surname,  bDate, null);
            this.pensja = pensja;
        }

        //SET and GET
        public double getPensja() {
            return pensja;
        }
        public void setPensja(double pensja) {
            this.pensja = pensja;
        }
}
