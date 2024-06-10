package com.pjatk_gui;

import java.util.ArrayList;
import java.util.List;

public class GymRoom {
    private static List<Integer> lID = new ArrayList<>();
    private int iD;
    private String name;
    private List<GymClass> lClasses;
    public GymRoom(int iD, String name)
        throws RoomDuplicateException{
        setID(iD);
        this.name = name;
        lClasses = new ArrayList<>();
    }
    

    public void addGymActivity(GymClass gC){
        lClasses.add(gC);
    }

    //SET and GET
    public void setID(Integer iD)
        throws RoomDuplicateException{
        if(lID.contains(iD))
            throw new RoomDuplicateException("Duplicated room number");
        else    
            this.iD = iD;
    }
}

class RoomDuplicateException
    extends Exception{
        public RoomDuplicateException(String msg){
            super(msg);
        }
    }