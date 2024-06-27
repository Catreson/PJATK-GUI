package com.pjatk_gui;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class GymRoomModel 
    implements ISaveable{
    private ObservableList<GymRoom> lGymRooms = FXCollections.observableArrayList();
    private String filename;
    private static GymRoomModel model;

    private GymRoomModel(){
        lGymRooms = FXCollections.observableArrayList();
        filename = App.class.getResource("rooms.globo").toString().replace("file:/", "");
        load(filename);
        lGymRooms.addListener(
            (ListChangeListener.Change<?> e) -> Supervisor.getSupervisor().add(""+e)
        );
    }

    public static GymRoomModel getModel(){
        if (model == null)
            model = new GymRoomModel();
        return model;
    }

    public void add(GymRoom gR){
        lGymRooms.add(gR);
        save(filename);
    }

    public void remove(GymRoom gR){
        lGymRooms.remove(gR);
        save(filename);
    }

    public List<Integer> getIDs(){
        return lGymRooms.stream().map( gR -> gR.getID()).collect(Collectors.toList());
    }

    public GymRoom getByID(Integer iD){
        return lGymRooms.stream().filter( gR -> gR.getID().equals(iD)).findFirst().get();
    }

    @Override
    public void load(String filename) {
        ObservableList<GymRoom> tmp = loadAll(filename);
        lGymRooms = tmp != null ? tmp : lGymRooms;
    }

    @Override
    public void save(String filename) {
        saveAll(lGymRooms, filename);
    }


}
