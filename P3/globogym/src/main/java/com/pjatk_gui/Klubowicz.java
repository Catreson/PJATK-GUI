package com.pjatk_gui;

import java.time.LocalDate;

import javafx.scene.image.Image;

public class Klubowicz 
    extends Osoba{
        private LocalDate passValitTo;
        private double accountBalance;
        

        public Klubowicz(String login, Password hPassword, String name, String surname, LocalDate bDate)
        throws LoginDuplicateException{
            this( login,  hPassword,  name,  surname,  bDate, null, LocalDate.now().plusDays(1), 0.);
        }
        public Klubowicz(String login, Password hPassword, String name, String surname, LocalDate bDate, Image profilePic)
            throws LoginDuplicateException{
            this( login,  hPassword,  name,  surname,  bDate, profilePic, LocalDate.now().plusDays(1), 0.);
        }
        public Klubowicz(String login, Password hPassword, String name, String surname, LocalDate bDate, Image profilePic, LocalDate passValidTo, double accountBalance)
            throws LoginDuplicateException{
            super( login,  hPassword,  name,  surname,  bDate, profilePic);
            this.accountBalance = accountBalance;
            this.passValitTo = passValidTo;
            OsobaModel.getModel().save();
        }

        public void joinClass(GymClass gC)
             throws AddingKlubowiczException{
            gC.add(this);
        }
        public void quitClass(GymClass gC){
            gC.remove(this);
        }

        public boolean verifyPass(){
            return LocalDate.now().isBefore(passValitTo);
        }

        public boolean butMonthlyPass(){
            if(accountBalance >= Prices.getPrices().getMonthlyPrice()){
                accountBalance -= Prices.getPrices().getMonthlyPrice();
                passValitTo = verifyPass() ? passValitTo.plusDays(30) : LocalDate.now().plusDays(30);
                OsobaModel.getModel().save();
                return true;
            }
            return false;
        }


        //SET and GET
        public double getAccountBalance() {
            return accountBalance;
        }
        public void setAccountBalance(double accountBalance) {
            this.accountBalance = accountBalance;
            OsobaModel.getModel().save();
        }
        public LocalDate getPassValitTo() {
            return passValitTo;
        }
}
