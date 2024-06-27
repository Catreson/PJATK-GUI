package com.pjatk_gui;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class OsobaModel 
    implements ISaveable{
    private static OsobaModel model;
    private ObservableList<Osoba> lOsobas = FXCollections.observableArrayList();
    private String filename;

    private OsobaModel(){
        System.out.println(App.class.getResource("osobas.globo").toString().replace("file:/", ""));
        filename = App.class.getResource("osobas.globo").toString().replace("file:/", "");
        load(App.class.getResource("osobas.globo").toString().replace("file:/", ""));
        lOsobas.addListener(
            (ListChangeListener.Change<? extends Osoba> e) -> Supervisor.getSupervisor().add(""+e)
        );
    }
    
    public static OsobaModel getModel(){
        if (model == null)
            model = new OsobaModel();
        return model;
    }
    public void add(Osoba o){
        lOsobas.add(o);
        save(App.class.getResource("osobas.globo").toString().replace("file:/", ""));
    }
    public void remove(Osoba o){
        lOsobas.remove(o);
        save(App.class.getResource("osobas.globo").toString().replace("file:/", ""));
    }
    public boolean contains(String u){
        return lOsobas.stream().anyMatch(o -> o.getLogin().equals(u));
    }
    public Osoba getByLogin(String u){
        return lOsobas.stream().filter(o -> o.getLogin().equals(u)).findFirst().get();
    }
    public List<Pracownik> getPracowniks(){
        return lOsobas.stream().filter( o -> (o instanceof Pracownik)).map( o -> (Pracownik)o).collect(Collectors.toList());
    }
    public List<Klubowicz> getKlubowiczs(){
        return lOsobas.stream().filter( o -> (o instanceof Klubowicz)).map( o -> (Klubowicz)o).collect(Collectors.toList());
    }
    public List<Manager> getManagers(){
        return lOsobas.stream().filter( o -> (o instanceof Manager)).map( o -> (Manager)o).collect(Collectors.toList());
    }
    @Override
    public void save(String filename) {
        saveAll(lOsobas, filename);
    }
    public void save(){
        save(filename);
    }
    @Override
    public void load(String filename) {
        ObservableList<Osoba> tmp = loadAll(filename);
        lOsobas = tmp != null ? tmp : lOsobas;
    }
}
