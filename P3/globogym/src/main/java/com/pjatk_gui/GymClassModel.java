package com.pjatk_gui;

import java.util.ArrayList;
import java.util.List;

public class GymClassModel 
    implements ISaveable{
    private static List<GymClass> lGymRooms = new ArrayList<>();

    @Override
    public void load(String filename) {
        lGymRooms = loadAll(filename);
    }

    @Override
    public void save(String filename) {
        saveAll(lGymRooms, filename);
    }


}
