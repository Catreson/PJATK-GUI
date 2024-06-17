package com.pjatk_gui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface ISaveable 
    extends Serializable{
        public void load(String filename);
        public void save(String filename);

        default public void saveAll(ObservableList<? extends Serializable> lS, String filename){
        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename))){
            output.writeObject(new ArrayList<>(lS));
            output.flush();
        }
        catch(IOException e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
        @SuppressWarnings("unchecked")
        default public <T extends Serializable> ObservableList<T> loadAll(String filename){
            ObservableList<T> lS = null;
            try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename))){
                lS = FXCollections.observableArrayList((ArrayList<T>) input.readObject());
            }
            catch(IOException e){
                System.out.println(e);
                e.printStackTrace();
            }
            catch(ClassNotFoundException e){
                System.out.println(e);
                e.printStackTrace();
            }
            System.out.println("Oto load: " + lS);
            return lS;
    }
}
