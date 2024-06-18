package com.pjatk_gui;

import java.time.LocalDate;

import javafx.scene.image.Image;

public class Manager 
    extends Pracownik{
        
    private String stylZarzadzania;


    public Manager(String login, Password hPassword, String name, String surname, LocalDate bDate, Image profilePic, double pensja, String stylZarzadzania)
        throws LoginDuplicateException{
        super(login, hPassword, name, surname, bDate, profilePic, pensja);
        this.stylZarzadzania = stylZarzadzania;
    }
    public Manager(String login, Password hPassword, String name, String surname, LocalDate bDate, double pensja, String stylZarzadzania)
        throws LoginDuplicateException{
        this(login, hPassword, name, surname, bDate, null,  pensja, stylZarzadzania);
    }   

    //SET and GET
    public String getStylZarzadzania() {
        return stylZarzadzania;
    }
    public void setStylZarzadzania(String stylZarzadzania) {
        this.stylZarzadzania = stylZarzadzania;
    }
}
