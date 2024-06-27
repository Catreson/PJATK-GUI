package com.pjatk_gui;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Supervisor 
    implements ISaveable{

    private static Supervisor supervisor;
    private String filename;
    private ObservableList<String> lChanges;
    private boolean listen;
    private Supervisor(){
        listen = false;
        lChanges = FXCollections.observableArrayList();
        filename = App.class.getResource("supervisor.globo").toString().replace("file:/", "");
        load(filename);
        if (lChanges.size() > 0)
            listen = true;
    }

    public static Supervisor getSupervisor(){
        if (supervisor == null) 
            supervisor = new Supervisor();
        return supervisor;
    }

    public void add(String s){
        System.out.println(s);
        if (listen){
            lChanges.add(s);
            save();
        }
        
    }

    public void start(){
        listen = true;
    }

    public List<String> stop(){
        listen = false;
        ObservableList<String> tmp = FXCollections.observableArrayList(lChanges);
        lChanges = FXCollections.observableArrayList();
        save();
        return tmp;
    }

    @Override
    public void save(String filename) {
        saveAll(lChanges, filename);
    }
    public void save(){
        save(filename);
    }
    @Override
    public void load(String filename) {
        ObservableList<String> tmp = loadAll(filename);
        lChanges = tmp != null ? tmp : lChanges;
    }
}
