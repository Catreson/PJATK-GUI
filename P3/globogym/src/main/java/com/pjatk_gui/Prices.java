package com.pjatk_gui;

import java.util.HashMap;

public class Prices {
    private HashMap<String, Double> priceMap;
    private static Prices prices;
    private Prices(){
        priceMap = new HashMap<>();
    }
    public static Prices getPrices(){
        if (prices == null)
            prices = new Prices();
        return prices;
    }

    public void setPrice(String o, double price){
        priceMap.put(o, price);
    }

    public double getPrice(String o){
        return priceMap.getOrDefault(o, 0.);
    }

    public double getMonthlyPrice(){
        return priceMap.get("monthly");
    }
}
