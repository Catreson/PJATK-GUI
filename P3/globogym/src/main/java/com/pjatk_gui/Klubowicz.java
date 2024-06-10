package com.pjatk_gui;

import java.time.LocalDate;

import javafx.scene.image.Image;

public class Klubowicz 
    extends Osoba{
        private LocalDate passValitTo;
        private double accountBalance;
        


        public Klubowicz(String login, String hPassword, String name, String surname, LocalDate bDate, Image profilePic){
            this( login,  hPassword,  name,  surname,  bDate, profilePic, LocalDate.now(), 0.);
        }
        public Klubowicz(String login, String hPassword, String name, String surname, LocalDate bDate, Image profilePic, LocalDate passValidTo, double accountBalance){
            super( login,  hPassword,  name,  surname,  bDate, profilePic);
            this.accountBalance = accountBalance;
            this.passValitTo = passValidTo;
        }
        public void joinClass(){}
        public void quitClass(){}

        public boolean verifyPass(){
            return LocalDate.now().isAfter(passValitTo) ? false : true;
        }

        //SET and GET
        public double getAccountBalance() {
            return accountBalance;
        }

        public void setAccountBalance(double accountBalance) {
            this.accountBalance = accountBalance;
        }
}
