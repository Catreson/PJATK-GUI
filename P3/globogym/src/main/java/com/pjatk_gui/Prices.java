package com.pjatk_gui;

public class Prices {
    private double monthlyPrice;
    private static Prices prices;
    private Prices(){}
    public Prices getPrices(){
        if (prices == null)
            prices = new Prices();
        return prices;
    }

    public double getMonthlyPrice(){
        return monthlyPrice;
    }
}
