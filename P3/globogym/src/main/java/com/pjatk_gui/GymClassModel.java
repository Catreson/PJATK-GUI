package com.pjatk_gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GymClassModel 
    implements ISaveable{
    private static ObservableList<GymClass> lGymRooms = FXCollections.observableArrayList();

    @Override
    public void load(String filename) {
        lGymRooms = loadAll(filename);
    }

    @Override
    public void save(String filename) {
        saveAll(lGymRooms, filename);
    }


}
