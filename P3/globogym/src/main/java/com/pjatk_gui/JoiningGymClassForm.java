package com.pjatk_gui;

import java.time.LocalDate;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class JoiningGymClassForm 
    extends BorderPane{

        private ObservableList<GymClass> lClasses;
        private Label lSuccess;

        public JoiningGymClassForm(Klubowicz k){
            super();

            lSuccess = new Label("Wybierz zajęcia");
            lClasses = FXCollections.observableArrayList(GymClassModel.getModel().getClasses().stream().filter(gC -> (gC.getDate().isAfter(LocalDate.now()) && gC.getDate().isBefore(k.getPassValitTo()))).collect(Collectors.toList()));
            System.out.println(lClasses);
            System.out.println(GymClassModel.getModel().getClasses());
            ListView<GymClass> listView = new ListView<>();
            listView.setCellFactory(
                (ListView<GymClass> par) -> {
                    return new ListCell<>(){
                    Label fDate = new Label();
                    Label fTime = new Label();
                    Label lRoom = new Label();
                    Label fRoom = new Label();
                    Label fLimit = new Label();
                    Button bJoin = new Button("Dołącz do zajęć");
                    HBox hB  = new HBox(fDate, fTime, lRoom, fRoom, fLimit, bJoin);
                    @Override
                    protected void updateItem(GymClass gC, boolean b){
                        if(gC != null){
                            fDate.setText(" "+gC.getDate());
                            fTime.setText(" "+gC.getDateTimeStart().getHour());
                            lRoom.setText("Sala: ");
                            fRoom.setText(" " + gC.getGymRoom());
                            fLimit.setText(" " + String.format("%d/%d", gC.getNoKlubowiczs(), gC.getLimit()));
                            if(gC.getNoKlubowiczs() <= gC.getLimit() &&  !gC.isMember(k)){
                                bJoin.setVisible(true);
                                bJoin.setOnAction(
                                    e -> {
                                        try {
                                            join(k, gC);
                                        } catch (AddingKlubowiczException ee) {
                                            lSuccess.setText(ee.getMessage());
                                        }
                                    }
                                );
                            }
                            else{
                                bJoin.setVisible(false);
                            }
                            setGraphic(hB);
                        }
                    }
                };
                }
            );
            listView.setItems(lClasses);
            setCenter(listView);
            setTop(lSuccess);
        }
        private void join(Klubowicz k, GymClass gC)
            throws AddingKlubowiczException{
            gC.add(k);
        }
}