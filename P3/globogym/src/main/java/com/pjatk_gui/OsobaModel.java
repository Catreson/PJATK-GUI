package com.pjatk_gui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OsobaModel 
    implements ISaveable{
    private static OsobaModel model;
    private List<Osoba> lOsobas = new ArrayList<>();

    private OsobaModel(){
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
    @Override
    public void save(String filename) {
        saveAll(lOsobas, filename);
    }
    @Override
    public void load(String filename) {
        lOsobas = loadAll(filename);
    }
}
