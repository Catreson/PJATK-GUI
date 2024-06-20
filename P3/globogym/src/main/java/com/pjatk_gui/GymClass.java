package com.pjatk_gui;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GymClass 
    implements Serializable{
    private static AtomicInteger iDIncrement = new AtomicInteger(0);
    
    private int iD;
    private String name;
    private Pracownik pracownik;
    private LocalDateTime start;
    private LocalDateTime end;
    private int limitKlubowiczs;
    private List<Klubowicz> lKlubowiczs;

    public GymClass(String name, Pracownik pracownik, LocalDateTime start, LocalDateTime end, int limitKlubowiczs){
        this.iD = iDIncrement.incrementAndGet();
        this.name = name;
        this.pracownik = pracownik;
        this.start = start;
        this.end = end;
        this.limitKlubowiczs = limitKlubowiczs;
        lKlubowiczs = new ArrayList<>();
    }

    public void add(Klubowicz k)
        throws AddingKlubowiczException{
        if(start.isBefore(LocalDateTime.now()))
            throw new AddingKlubowiczException("Class already started");
        if(!k.verifyPass())
            throw new AddingKlubowiczException("Klubowiczs pass has expired");
        lKlubowiczs.add(k);
    }
    public void remove(Klubowicz k){
        lKlubowiczs.remove(k);
    }
}
class AddingKlubowiczException
    extends Exception{
        public AddingKlubowiczException(String msg){
            super(msg);
        }
    }