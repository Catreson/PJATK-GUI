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
    private List<Klubowicz> lKlubowiczs;

    public GymClass(String name, Pracownik pracownik, LocalDateTime start, LocalDateTime end){
        this.iD = iDIncrement.incrementAndGet();
        this.name = name;
        this.pracownik = pracownik;
        this.start = start;
        this.end = end;
        lKlubowiczs = new ArrayList<>();
    }

    public void add(Klubowicz k){
        lKlubowiczs.add(k);
    }
    public void remove(Klubowicz k){
        lKlubowiczs.remove(k);
    }
}
