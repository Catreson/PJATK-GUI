package com.pjatk_gui;

import java.time.LocalDate;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ManagingGymClassForm 
    extends BorderPane{

        private ObservableList<GymClass> lClasses;
        private Label lSuccess;
        @SuppressWarnings("unused")
        private Pracownik pracownik;

        public ManagingGymClassForm(Pracownik p){
            super();
            this.pracownik = p;
            lSuccess = new Label("Wybierz zajęcia");
            lClasses = FXCollections.observableArrayList(GymClassModel.getModel().getClasses().stream().filter(gC -> (gC.getDate().isAfter(LocalDate.now()))).collect(Collectors.toList()));
            System.out.println(lClasses);
            System.out.println(GymClassModel.getModel().getClasses());
            ListView<GymClass> listView = new ListView<>();
            listView.setEditable(true);
            listView.setCellFactory(
                (ListView<GymClass> par) -> {
                    return new ListCell<>(){
                    Label fDate = new Label();
                    Label fTime = new Label();
                    Label lRoom = new Label();
                    Label fRoom = new Label();
                    Label fLimit = new Label();
                    Button bRemove = new Button("Usuń");
                    Button bAssign = new Button("Zmień pracownika");
                    Button bAdd = new Button("Dodaj klubowicza");
                    Button bKill = new Button("Usuń klubowicza");
                    HBox hB  = new HBox(fDate, fTime, lRoom, fRoom, fLimit, bRemove, bAssign, bAdd, bKill);
                    @Override
                    protected void updateItem(GymClass gC, boolean b){
                        super.updateItem(gC, b);
                        if(gC == null || b)
                        {
                            setText(null);
                            setGraphic(null);
                        }
                        else if(gC != null){
                            fDate.setText(" "+gC.getDate());
                            fTime.setText(" "+gC.getDateTimeStart().getHour());
                            lRoom.setText("\tSala: ");
                            fRoom.setText(" " + gC.getGymRoom());
                            fLimit.setText(" " + String.format("%d/%d", gC.getNoKlubowiczs(), gC.getLimit()));
                            bRemove.setOnAction(
                                e -> {
                                    lClasses.remove(gC);
                                    GymClassModel.getModel().remove(gC);
                                    listView.refresh();
                                    System.out.println("Refresh");
                                }
                            );
                            bAssign.setOnAction(
                                e -> {
                                    assign(gC);
                                }
                            );
                            if(gC.isAssignedTo(p)){
                                bAdd.setVisible(true);
                                bAdd.setOnAction(
                                    e -> add(gC)
                                );
                                bKill.setVisible(true);
                                bKill.setOnAction(
                                    e -> kill(gC)
                                );
                            }
                            else{
                                bAdd.setVisible(false);
                                bKill.setVisible(false);
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
        private void add(GymClass gC){
                Stage stage = new Stage();
                HBox hBox = new HBox(5);
                Scene scene = new Scene(hBox, 250, 40);
                hBox.setStyle(App.STAJL_STRING);
                stage.setTitle("Wybierz klubowicza");
                ObservableList<String> lKlubowiczs = FXCollections.observableArrayList(OsobaModel.getModel().getKlubowiczs().stream().filter( k -> !gC.isMember(k)).map( p -> p.getLogin()).collect(Collectors.toList()));
                ComboBox<String> listView = new ComboBox<>();
                listView.setItems(lKlubowiczs);
                Label lSuccess = new Label();
                Button bAdd = new Button("Dodaj do zajęć");
                bAdd.setOnAction(
                    e -> {
                        try{
                        gC.add((Klubowicz)OsobaModel.getModel().getByLogin(listView.getValue()));
                        stage.hide();}
                        catch(AddingKlubowiczException ee){
                            lSuccess.setText(ee.getMessage());
                        }
                    }
                );
                hBox.getChildren().addAll(listView, bAdd, lSuccess);
                stage.setScene(scene);
                stage.getIcons().add(App.icon);
                stage.showAndWait();
        }

        private void kill(GymClass gC){
            Stage stage = new Stage();
            HBox hBox = new HBox(5);
            Scene scene = new Scene(hBox, 250, 40);
            hBox.setStyle(App.STAJL_STRING);
            stage.setTitle("Wybierz klubowicza");
            ObservableList<String> lKlubowiczs = FXCollections.observableArrayList(gC.getLKlubowiczs().stream().map( k -> k.getLogin()).collect(Collectors.toList()));
            ComboBox<String> listView = new ComboBox<>();
            listView.setItems(lKlubowiczs);
            Label lSuccess = new Label();
            Button bAdd = new Button("Usuń z zajęć");
            bAdd.setOnAction(
                e -> {
                    gC.remove((Klubowicz)OsobaModel.getModel().getByLogin(listView.getValue()));
                    stage.hide();
                }
            );
            hBox.getChildren().addAll(listView, bAdd, lSuccess);
            stage.setScene(scene);
            stage.getIcons().add(App.icon);
            stage.showAndWait();
    }

        private void assign(GymClass gC){
            Stage stage = new Stage();
            HBox hBox = new HBox(5);
            Scene scene = new Scene(hBox, 150, 40);
            hBox.setStyle(App.STAJL_STRING);
            stage.setTitle("Wybierz pracownika");
            ObservableList<String> lPracowniks = FXCollections.observableArrayList(OsobaModel.getModel().getPracowniks().stream().map( p -> p.getLogin()).collect(Collectors.toList()));
            ComboBox<String> listView = new ComboBox<>();
            listView.setItems(lPracowniks);
            Button bAdd = new Button("Przypisz");
            bAdd.setOnAction(
                e -> {
                    gC.setPracownik((Pracownik)OsobaModel.getModel().getByLogin(listView.getValue()));
                    stage.hide();
                }
            );
            hBox.getChildren().addAll(listView, bAdd);
            stage.setScene(scene);
            stage.getIcons().add(App.icon);
            stage.showAndWait();
        }   
}