package com.pjatk_gui;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class GymClass 
    implements Serializable{
    private static AtomicInteger iDIncrement = new AtomicInteger(0);
    
    private int iD;
    private String name;
    private Pracownik pracownik;
    private LocalDateTime start;
    private LocalDate day;
    private LocalDateTime end;
    private int limitKlubowiczs;
    private GymRoom gymRoom;
    private List<Klubowicz> lKlubowiczs;

    public GymClass(String name, Pracownik pracownik, LocalDateTime start, LocalDateTime end, int limitKlubowiczs, int gymRoom)
        throws AddingGymClassException{
        this.iD = iDIncrement.incrementAndGet();
        this.name = name;
        this.pracownik = pracownik;
        this.start = start;
        this.end = end;
        this.day = start.toLocalDate();
        System.out.println(this.day);
        System.out.println(LocalDate.now());
        if(this.day.isBefore(LocalDate.now())){
            System.out.println("EXCEPTION");
            throw new AddingGymClassException("Class in past");
        }
        this.limitKlubowiczs = limitKlubowiczs;
        this.gymRoom = GymRoomModel.getModel().getByID(gymRoom);
        List<GymClass> gymClasses = GymClassModel.getModel().getClasses().stream().filter( gC -> gC.getGymRoom().equals(this.gymRoom)).collect(Collectors.toList());
        if(gymClasses.stream().anyMatch( gC -> collision(gC.getDateTimeStart(), gC.getDateTimeEnd())))
            throw new AddingGymClassException("Time collision");
        lKlubowiczs = new ArrayList<>();
        GymClassModel.getModel().add(this);
    }

    public void add(Klubowicz k)
        throws AddingKlubowiczException{
        if(start.isBefore(LocalDateTime.now()))
            throw new AddingKlubowiczException("Class already started");
        if(!k.verifyPass())
            throw new AddingKlubowiczException("Klubowiczs pass has expired");
        if(getNoKlubowiczs() >= limitKlubowiczs)
            throw new AddingKlubowiczException("Limit of Klubowiczs");
        lKlubowiczs.add(k);
        GymClassModel.getModel().save();
    }
    public void remove(Klubowicz k){
        lKlubowiczs = lKlubowiczs.stream().filter( kl -> ! kl.getLogin().equals(k.getLogin())).collect(Collectors.toList());
        GymClassModel.getModel().save();
    }
    public boolean isMember(Klubowicz k){
        return lKlubowiczs.stream().anyMatch( kl -> kl.getLogin().equals(k.getLogin()));
    }

    public boolean collision(LocalDateTime s, LocalDateTime e){
        System.out.println("Detection");
        if(start.isBefore(e) && !start.isBefore(s))
            return true;
        if(!end.isAfter(e) && end.isAfter(s))
            return true;
        if(start.isBefore(s) && end.isAfter(e))
            return true;
        if(start.equals(s) || end.equals(e))
            return true;
        return false;
    }

    @Override
    public String toString(){
        return String.format("Name: %s\nGymRoom: %d\nDay: %s\nPracownik: %s\nLiczba uczestnikow: %d", name, gymRoom.getID(), getDate().toString(), pracownik.getLogin(), getNoKlubowiczs());
    }

    //SET and GET
    public String getName(){
        return name;
    }

    public int getID(){
        return iD;
    }

    public List<Klubowicz> getLKlubowiczs(){
        return lKlubowiczs;
    }

    public int getNoKlubowiczs(){
        return lKlubowiczs.size();
    }

    public int getLimit(){
        return limitKlubowiczs;
    }

    public LocalDateTime getDateTime(){
        return start;
    }
    public LocalDateTime getDateTimeStart(){
        return start;
    }
    public LocalDateTime getDateTimeEnd(){
        return end;
    }
    public GymRoom getGymRoom(){
        return gymRoom;
    }
    public LocalDate getDate(){
        return day;
    }
    public Integer getePercent(){
        return (int)(100 * (double)getNoKlubowiczs()/limitKlubowiczs);
    }
    public boolean isAssignedTo(Pracownik p){
        return pracownik.getLogin().equals(p.getLogin());
    }

    public void setPracownik(Pracownik p){
        this.pracownik = p;
        OsobaModel.getModel().save();
    }
}
class AddingKlubowiczException
    extends Exception{
        public AddingKlubowiczException(String msg){
            super(msg);
        }
}
class AddingGymClassException
    extends Exception{
        public AddingGymClassException(String msg){
            super(msg);
        }
}