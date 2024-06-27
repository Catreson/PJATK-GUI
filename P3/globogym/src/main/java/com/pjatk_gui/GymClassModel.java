package com.pjatk_gui;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class GymClassModel 
    implements ISaveable{
    private ObservableList<GymClass> lGymClasses = FXCollections.observableArrayList();
    private String filename;
    private static GymClassModel model;

    private GymClassModel(){
        lGymClasses = FXCollections.observableArrayList();
        filename = App.class.getResource("classes.globo").toString().replace("file:/", "");
        load(filename);
        lGymClasses.addListener(
            (ListChangeListener.Change<?> e) -> Supervisor.getSupervisor().add(""+e)
        );
    }

    public static GymClassModel getModel(){
        if (model == null)
            model = new GymClassModel();
        return model;
    }

    public List<GymClass> getClasses(){
        return lGymClasses;
    }

    public List<GymClass> getClassesByUser(Klubowicz k){
        return lGymClasses.stream().filter(gC -> gC.isMember(k)).collect(Collectors.toList());
    }

    public void add(GymClass gC){
        lGymClasses.add(gC);
        save(filename);
    }

    public void remove(GymClass gC){
        //lGymClasses = FXCollections.observableArrayList(lGymClasses.stream().filter(gCl -> gCl.getID() == gC.getID()).collect(Collectors.toList()));
        lGymClasses = lGymClasses.filtered(gCl -> gCl.getID() != gC.getID());
        save(filename);
    }

    @Override
    public void load(String filename) {
        ObservableList<GymClass> tmp = loadAll(filename);
        lGymClasses = tmp != null ? tmp : lGymClasses;
    }

    @Override
    public void save(String filename) {
        saveAll(lGymClasses, filename);
    }
    public void save() {
        saveAll(lGymClasses, filename);
    }

}
