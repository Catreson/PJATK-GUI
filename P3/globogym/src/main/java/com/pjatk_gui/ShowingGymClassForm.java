package com.pjatk_gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ShowingGymClassForm 
    extends BorderPane{

        private ObservableList<GymClass> lClasses;
        private HBox topBox;
        private ListView<GymClass> listView;
        private Label lFilter;
        private TextField fFilter;
        private Label lLimit;
        private TextField fLimit;
        

        public ShowingGymClassForm(){
            super();
            topBox = new HBox();
            lFilter = new Label("Szukana fraza: ");
            fFilter = new TextField();
            lLimit = new Label("Minimalny % frekwencji");
            fLimit = new TextField();
            fLimit.textProperty().addListener(new ChangeListener<String>() {
            @Override
             public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    fLimit.setText(oldValue);
                }
            }
            });
            fFilter.setOnAction(
                e -> {
                    filter();
                }
            );
            fLimit.setOnAction(
                e -> {
                    limit();
                }
            );
            topBox.getChildren().addAll(lFilter, fFilter, lLimit, fLimit);
            lClasses = FXCollections.observableArrayList(GymClassModel.getModel().getClasses());
            listView = new ListView<>();
            listView.setCellFactory(
                (ListView<GymClass> par) -> {
                    return new ListCell<>(){
                    Label fName = new Label();
                    Label fSala = new Label();
                    Label fLimit = new Label();
                    Label fPercent = new Label();
                    HBox hB  = new HBox(fName, fSala, fLimit, fPercent);
                    @Override
                    protected void updateItem(GymClass gC, boolean b){
                        super.updateItem(gC, b);
                        if (b || gC == null) {
                            setText(null);
                            setGraphic(null);
                        }
                        else if(gC != null){
                            fName.setText(" "+gC.getName());
                            fSala.setText(" "+gC.getGymRoom().getName() + " " + gC.getGymRoom().getID());
                            fLimit.setText(" " + String.format("%d/%d", gC.getNoKlubowiczs(), gC.getLimit()));
                            fPercent.setText(" " + gC.getePercent() + "%");
                            setGraphic(hB);
                        }
                    }
                };
                }
            );
            listView.setItems(lClasses);
            setCenter(listView);
            setTop(topBox);
        }

        private void filter(){
            String str = fFilter.getText();
            ObservableList<GymClass> lTMPClasses = lClasses.filtered( gC -> gC.getName().contains(str));
            listView.setItems(lTMPClasses);
        }
        
        private void limit(){
            Integer limit = Integer.parseInt(fLimit.getText());
            ObservableList<GymClass> lTMPClasses = lClasses.filtered( gC -> gC.getePercent() >= limit);
            listView.setItems(lTMPClasses);
        }
}