package com.pjatk_gui;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OsobaModel 
    implements ISaveable{
    private static OsobaModel model;
    private ObservableList<Osoba> lOsobas = FXCollections.observableArrayList();

    private OsobaModel(){
        //System.out.println(App.class.getResource("osobas.globo").toString());
    }
    
    public static OsobaModel getModel(){
        if (model == null)
            model = new OsobaModel();
        return model;
    }
    public void add(Osoba o){
        lOsobas.add(o);
    }
    public void remove(Osoba o){
        lOsobas.remove(o);
    }
    public boolean contains(String u){
        return lOsobas.stream().anyMatch(o -> o.getLogin().equals(u));
    }
    public Osoba getByLogin(String u){
        return lOsobas.stream().filter(o -> o.getLogin().equals(u)).findFirst().get();
    }
    @Override
    public void save(String filename) {
        saveAll(lOsobas, filename);
    }
    @Override
    public void load(String filename) {
        lOsobas = loadAll(filename);
    }
}
