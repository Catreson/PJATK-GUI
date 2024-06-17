package com.pjatk_gui;

import java.time.LocalDate;

public class Pracownik 
    extends Osoba{

        private double pensja;

        public Pracownik(String login, Password hPassword, String name, String surname, LocalDate bDate, String profilePic, double pensja){
            super( login,  hPassword,  name,  surname,  bDate, profilePic);
            this.pensja = pensja;
        }
        public Pracownik(String login, Password hPassword, String name, String surname, LocalDate bDate, double pensja){
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
