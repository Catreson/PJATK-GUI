package com.pjatk_gui;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class KlubowiczView 
    extends BorderPane{
        

        public KlubowiczView(){
            super();
            VBox vB = new VBox();
            Label l = new Label("TEST");
            DatePicker dP = new DatePicker();
            vB.getChildren().addAll(l, dP);
            setCenter(vB);
        }
}
