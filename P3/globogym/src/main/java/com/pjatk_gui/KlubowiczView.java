package com.pjatk_gui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class KlubowiczView 
    extends BorderPane{
        private ObservableList<GymDay> lDays;
        private List<GymClass> userClasses;
        private Label lUserClasses;
        private Klubowicz klubowicz;
        private ListView<GymDay> classesView;
        private Label lNoClasses;
        private Label fNoClasses;
        private Label lNoClassesLeft;
        private Label fNoClassesLeft;
    
        public KlubowiczView(Klubowicz k){
            super();
            this.klubowicz = k;
            setStyle(App.BOX_STAJL);
            userClasses = GymClassModel.getModel().getClassesByUser(k);
            System.out.println(userClasses);
            System.out.println(GymClassModel.getModel().getClasses());
            lUserClasses = new Label("Twoje zajęcia:");
            lUserClasses.setStyle(App.TITLE_STAJL);
            lDays = FXCollections.observableArrayList(dateSpan(LocalDate.now().withDayOfMonth(1), LocalDate.now().withDayOfMonth(LocalDate.now().getMonth().length(LocalDate.now().isLeapYear()))));
            VBox leftBox = new VBox(3);
            VBox.setVgrow(leftBox, Priority.ALWAYS);
            leftBox.setStyle(App.BOX_STAJL);
            classesView = new ListView<>();
            classesView.setMinWidth(350);
            classesView.setCellFactory(
            (ListView<GymDay> par) -> {
                return new ListCell<>(){
                    Label fDate = new Label();
                    Label fTime = new Label();
                    Label lRoom = new Label();
                    Label fRoom = new Label();
                    Label fName = new Label();
                    Button bQuit = new Button("Opuść zajęcia");
                    HBox hB  = new HBox(fDate, fTime, lRoom, fRoom, fName, bQuit);
                    @Override
                    protected void updateItem(GymDay gD, boolean b){
                        super.updateItem(gD, b);
                        if (b || gD == null) {
                            setText(null);
                            setGraphic(null);
                        }
                        else if(gD != null){
                            fDate.setText(""+gD.getDate());
                            if(gD.getGymClass() != null){
                                bQuit.setVisible(true);
                                fTime.setText(" "+gD.getGymClass().getDateTime().toLocalTime());
                                lRoom.setText("Sala: ");
                                fRoom.setText(" "+gD.getGymClass().getGymRoom().getID());
                                fName.setText(" " + gD.getGymClass().getName() + "\t");
                                bQuit.setOnAction(
                                    e -> quit(k, gD)
                                );
                            }
                            else{
                                fTime.setText("");
                                lRoom.setText("");
                                fRoom.setText("");
                                fName.setText("");
                                bQuit.setVisible(false);
                            }
                            setGraphic(hB);
                        }
                    }
                };
            }
        );
        classesView.setItems(lDays);

        VBox rightBox = new VBox(3);
        Label lTitle = new Label("Opcje");
        Button bJoinClass = new Button("Dołącz do zajęć");
        rightBox.setStyle(App.BOX_STAJL);
        lTitle.setStyle(App.TITLE_STAJL);
        bJoinClass.setOnAction(
            e -> joinClass()
        );

        GridPane centerBox = new GridPane();
        centerBox.setStyle(App.BOX_STAJL);
        Label lStatistics = new Label("Statystyki na miesiąc " + Prices.getPrices().getMonth(LocalDate.now().getMonth().toString()));
        lStatistics.setStyle(App.TITLE_STAJL);
        lNoClasses = new Label("Liczba zajęć: ");
        fNoClasses = new Label("" + userClasses.size());
        lNoClassesLeft = new Label("Liczba nadchodzących zajęć: ");
        fNoClassesLeft = new Label("" + userClasses.stream().filter( gC -> gC.getDate().isAfter(LocalDate.now())).collect(Collectors.toList()).size());

        centerBox.add(lStatistics, 1, 1, 2, 1);
        centerBox.add(lNoClasses, 1, 2);
        centerBox.add(fNoClasses, 2, 2);
        centerBox.add(lNoClassesLeft, 1, 3);
        centerBox.add(fNoClassesLeft, 2, 3);
        rightBox.getChildren().addAll(lTitle, bJoinClass);
        leftBox.getChildren().addAll(lUserClasses, classesView);
        centerBox.setAlignment(Pos.CENTER);
        rightBox.setAlignment(Pos.CENTER);

        setLeft(leftBox);
        setRight(rightBox);
        setCenter(centerBox);
        //getChildren().addAll(leftBox, centerBox, rightBox);
    }

    private void quit(Klubowicz k, GymDay gD){
        gD.getGymClass().remove(k);
        gD.nullGymClass();
        userClasses = GymClassModel.getModel().getClassesByUser(k);
        fNoClasses.setText("" + userClasses.size());
        fNoClassesLeft.setText("" + userClasses.stream().filter( gC -> gC.getDate().isAfter(LocalDate.now())).collect(Collectors.toList()).size());
    }

    private void joinClass(){
        Stage addingStage = new Stage();
        BorderPane addingPane = new JoiningGymClassForm(klubowicz);
        addingPane.setStyle(App.STAJL_STRING);
        Scene addingScene = new Scene(addingPane, 600, 600);
        addingStage.setTitle("Dołączanie na zajęcia");
        addingStage.setScene(addingScene);
        addingStage.getIcons().add(App.icon);
        addingStage.showAndWait();
        userClasses = GymClassModel.getModel().getClassesByUser(klubowicz);
        lDays = FXCollections.observableArrayList(dateSpan(LocalDate.now().withDayOfMonth(1), LocalDate.now().withDayOfMonth(LocalDate.now().getMonth().length(LocalDate.now().isLeapYear()))));
        classesView.setItems(lDays);
        fNoClasses.setText("" + userClasses.size());
        fNoClassesLeft.setText("" + userClasses.stream().filter( gC -> gC.getDate().isAfter(LocalDate.now())).collect(Collectors.toList()).size());
    }

    private List<GymDay> dateSpan(LocalDate s, LocalDate e){
        List<GymDay> lD = new ArrayList<>();
        int i = 0;
        GymClass gymClass;
        while(!s.plusDays(i).isAfter(e)){
            final int temp = i;
            try{
            gymClass = userClasses.stream().filter( gC -> gC.getDate().equals(s.plusDays(temp))).findFirst().get();
            }
            catch(NoSuchElementException ee){
                gymClass = null;
            }
            lD.add(new GymDay(s.plusDays(temp), gymClass));
            i++;
        }
        return lD;
    }
}
class GymDay{
    private GymClass gC;
    private LocalDate date;
    public GymDay(LocalDate date, GymClass gC){
        this.date = date;
        this.gC = gC;
    }
    public GymClass getGymClass(){
        return gC;
    }
    public LocalDate getDate(){
        return date;
    }
    public void nullGymClass(){
        gC = null;
    }
}