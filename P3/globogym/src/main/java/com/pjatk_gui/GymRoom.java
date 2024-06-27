package com.pjatk_gui;

import java.io.Serializable;

public class GymRoom 
    implements Serializable{
    private int iD;
    private String name;
    public GymRoom(int iD, String name)
        throws RoomDuplicateException{
        setID(iD);
        this.name = name;
        GymRoomModel.getModel().add(this);
    }
    
    @Override
    public String toString(){
        return name + " " + iD;
    }

    //SET and GET

    public void setID(Integer iD)
        throws RoomDuplicateException{
        if(GymRoomModel.getModel().getIDs().stream().anyMatch( gI -> gI.equals(iD)))
            throw new RoomDuplicateException("Duplicated room number");
        else    
            this.iD = iD;
    }

    public Integer getID(){
        return iD;
    }

    public String getName(){
        return name;
    }
}

class RoomDuplicateException
    extends Exception{
        public RoomDuplicateException(String msg){
            super(msg);
        }
    }