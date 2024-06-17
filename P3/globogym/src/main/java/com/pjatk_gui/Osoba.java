package com.pjatk_gui;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Osoba 
    implements Serializable{
    protected static AtomicInteger iDIncrement = new AtomicInteger(0);

    protected int iD;
    private String name;
    private String surname;
    private String login;
    private Password hPassword;
    private LocalDate bDate;
    private String profilePic;




    public Osoba(String login, Password hPassword, String name, String surname, LocalDate bDate, String profilePic){
        this.iD = iDIncrement.incrementAndGet();
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.hPassword = hPassword;
        this.bDate = bDate;
        this.profilePic = profilePic;
        OsobaModel.getModel().add(this);
    }
    
    

    //SET and GET
    public Password gethPassword() {
        return hPassword;
    }
    public void sethPassword(Password hPassword) {
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
    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
    public LocalDate getbDate() {
        return bDate;
    }

    public void setbDate(LocalDate bDate) {
        this.bDate = bDate;
    }
}
