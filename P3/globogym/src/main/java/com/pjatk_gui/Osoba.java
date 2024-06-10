package com.pjatk_gui;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.scene.image.Image;

public class Osoba {
    protected int iD;
    protected static AtomicInteger iDIncrement = new AtomicInteger(0);
    private String name;
    private String surname;
    private String login;
    private String hPassword;
    private LocalDate bDate;
    private Image profilePic;


    public Osoba(String login, String hPassword, String name, String surname, LocalDate bDate, Image profilePic){
        this.iD = iDIncrement.incrementAndGet();
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.hPassword = hPassword;
        this.bDate = bDate;
        this.profilePic = profilePic;
    }
    



    //SET and GET
    public String gethPassword() {
        return hPassword;
    }
    public void sethPassword(String hPassword) {
        this.hPassword = hPassword;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
