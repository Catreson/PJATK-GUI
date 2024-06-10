package com.pjatk_gui;

import java.time.LocalDate;

public class Klubowicz 
    extends Osoba{
        private LocalDate passValitTo;
        private double accountBalance;
        

        public Klubowicz(String login, String hPassword, String name, String surname, LocalDate bDate){
            this( login,  hPassword,  name,  surname,  bDate, null, LocalDate.now(), 0.);
        }
        public Klubowicz(String login, String hPassword, String name, String surname, LocalDate bDate, String profilePic){
            this( login,  hPassword,  name,  surname,  bDate, profilePic, LocalDate.now(), 0.);
        }
        public Klubowicz(String login, String hPassword, String name, String surname, LocalDate bDate, String profilePic, LocalDate passValidTo, double accountBalance){
            super( login,  hPassword,  name,  surname,  bDate, profilePic);
            this.accountBalance = accountBalance;
            this.passValitTo = passValidTo;
        }

        public void joinClass(GymClass gC){
            gC.add(this);
        }
        public void quitClass(GymClass gC){
            gC.remove(this);
        }

        public boolean verifyPass(){
            return LocalDate.now().isAfter(passValitTo);
        }


        //SET and GET
        public double getAccountBalance() {
            return accountBalance;
        }

        public void setAccountBalance(double accountBalance) {
            this.accountBalance = accountBalance;
        }
}
