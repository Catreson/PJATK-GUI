package com.pjatk_gui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;


public class Prices 
    implements Serializable{
    private HashMap<String, Double> priceMap;
    private HashMap<String, String> monthMap;
    private String quoteOfADay;
    private String filename;
    private static Prices prices;
    private Prices(){
        priceMap = new HashMap<>();
        monthMap = new HashMap<>(){{
            put("JANUARY","Styczeń");
            put("FEBRUARY","Luty");
            put("MARCH","Marzec");
            put("APRIL","Kwiecień");
            put("MAY","Maj");
            put("JUNE","Czerwiec");
            put("JULY","Lipiec");
            put("AUGUST","Sierpień");
            put("SEPTEMBER","Wrzesień");
            put("OCTOBER","Pażdziernik");
            put("NOVEMBER","Listopad");
            put("DECEMBER","Grudzień");
        }};
        quoteOfADay = "Ceny nigdy nie są zbyt wysokie.";
        priceMap.put("monthly", 12.5);
        filename = App.class.getResource("prices.globo").toString().replace("file:/", "");
        System.out.println("Wczytano cytat: " + quoteOfADay);
    }
    public static Prices getPrices(){
        if (prices == null){
            prices = new Prices();
            prices.load();
        }
        return prices;
    }

    public void setPrice(String o, double price){
        priceMap.put(o, price);
        save();
    }

    public double getPrice(String o){
        return priceMap.getOrDefault(o, 0.);
    }

    public String getMonth(String s){
        return monthMap.getOrDefault(s, s);
    }

    public double getMonthlyPrice(){
        return priceMap.get("monthly");
    }

    public void setQuote(String q, Osoba o){
        if(o instanceof Manager){
            quoteOfADay = q;
            save();
        }

    }

    public String getQuote(){
        return quoteOfADay;
    }
    
    public void save(){
    try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename))){
            output.writeObject(this);
            output.flush();
        }
        catch(IOException e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public Prices read(){
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename))){
                prices = (Prices)input.readObject();
            }
            catch(IOException e){
                System.out.println(e);
                e.printStackTrace();
            }
            catch(ClassNotFoundException e){
                System.out.println(e);
                e.printStackTrace();
            }
            return prices;
    }

    public void load(){
        Prices tmp = read();
        prices = tmp != null ? tmp : prices;
    }

}
