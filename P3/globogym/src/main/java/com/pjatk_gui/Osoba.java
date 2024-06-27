package com.pjatk_gui;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.scene.image.Image;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;


public class Osoba 
    implements Serializable{
    protected static AtomicInteger iDIncrement = new AtomicInteger(0);

    protected int iD;

    private String name;
    private String surname;
    private String login;
    private Password hPassword;
    private LocalDate bDate;
    private transient Image profilePic;




    public Osoba(String login, Password hPassword, String name, String surname, LocalDate bDate, Image profilePic)
        throws LoginDuplicateException{
        if(OsobaModel.getModel().contains(login))
            throw new LoginDuplicateException("User already exists");
        this.iD = iDIncrement.incrementAndGet();
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.hPassword = hPassword;
        this.bDate = bDate;
        this.profilePic = profilePic != null ? profilePic : App.icon;
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

    public int getiD() {
        return iD;
    }

    public Image getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Image profilePic) {
        this.profilePic = profilePic;
        OsobaModel.getModel().save();
    }
    public LocalDate getbDate() {
        return bDate;
    }

    public void setbDate(LocalDate bDate) {
        this.bDate = bDate;
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        try {
            this.profilePic = (Image)SwingFXUtils.toFXImage(ImageIO.read(s), null);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        ImageIO.write(SwingFXUtils.fromFXImage(profilePic, null), "png", s);
    }
}

class LoginDuplicateException
    extends Exception{
        public LoginDuplicateException(String msg){
            super(msg);
        }
    }