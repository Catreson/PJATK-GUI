package com.w13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class Main {
    public static void main(String[] args){
        int[] tab = {10, 20, 30};

        System.out.println(
            Arrays.toString(tab)
        );

        {
            List<Integer> list = new ArrayList<>();
            list.add(10);
            list.add(20);
            list.add(30);
            System.out.println(list);
        }

        {
            ObservableList<Integer> oList = FXCollections.observableArrayList(10, 20, 30);
            System.out.println(oList);
            oList.add(40);
            oList.addListener(
                (ListChangeListener) e -> System.out.println(oList)
            );
            oList.add(100);
            //System.out.println(oList);
        }
    }
}
