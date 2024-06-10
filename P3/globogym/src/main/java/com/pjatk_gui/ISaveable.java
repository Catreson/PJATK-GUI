package com.pjatk_gui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface ISaveable 
    extends Serializable{
        public void load(String filename);
        public void save(String filename);

        default public void saveAll(List<? extends Serializable> lS, String filename){
        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename))){
            output.writeObject(lS);
            output.flush();
        }
        catch(IOException e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
        @SuppressWarnings("unchecked")
        default public <T extends Serializable> List<T> loadAll(String filename){
            List<T> lS = null;
            try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename))){
                lS = (List<T>) input.readObject();
            }
            catch(IOException e){
                System.out.println(e);
                e.printStackTrace();
            }
            catch(ClassNotFoundException e){
                System.out.println(e);
                e.printStackTrace();
            }
            return lS;
    }
}
