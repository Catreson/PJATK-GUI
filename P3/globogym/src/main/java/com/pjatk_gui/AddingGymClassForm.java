package com.pjatk_gui;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class AddingGymClassForm 
    extends GridPane{
        private ObservableList<String> lPracowniks;
        private ObservableList<Integer> lRooms;

        protected Button bAdd;
        protected Button bCancel;

        protected Label lTitle;
        protected Label lName;
        protected TextField fName;
        protected Label lPracownik;
        protected ComboBox<String> fPracownik;
        protected Label lSuccess;
        protected Label lGymRoom;
        protected ComboBox<Integer> fGymRoom;
        protected Label lDate;
        protected DatePicker fDate;
        protected Label lStart;
        protected Label lEnd;
        protected TextField fStart;
        protected TextField fEnd;
        protected Label lLimit;
        protected Spinner<Integer> fLimit;

        public AddingGymClassForm(){
            super();

            lPracowniks = FXCollections.observableArrayList(OsobaModel.getModel().getPracowniks().stream().map( p -> p.getLogin()).collect(Collectors.toList()));
            lRooms = FXCollections.observableArrayList(GymRoomModel.getModel().getIDs());
            
            bAdd= new Button("Dodaj");
            bCancel = new Button("Anuluj");
            bAdd.setOnAction(
                e -> {
                    try {
                        add();
                        Timeline timeline = new Timeline(
                            new KeyFrame(Duration.seconds(0), 
                            ee -> {
                                lSuccess.setText("DODANO");
                                lSuccess.setTextFill(Color.GREEN);
                            }),
                            new KeyFrame(Duration.seconds(2), 
                            ee -> {
                                getScene().getWindow().hide();
                            })
                        );
                        timeline.playFromStart();
                    } catch (InvalidGymClassException ee) {
                        lSuccess.setText(ee.getMessage());
                        lSuccess.setTextFill(Color.TOMATO);
                    }
                }
            );

            bCancel.setOnAction(
                e -> getScene().getWindow().hide()
            );

            lTitle = new Label("Dodawanie zajęć");
            lTitle.setStyle(App.TITLE_STAJL);
            lSuccess = new Label();
            lName = new Label("Nazwa: ");
            fName = new TextField();
            lPracownik = new Label("Pracownik: ");
            fPracownik = new ComboBox<>();
            lGymRoom = new Label("Numer sali: ");
            fGymRoom = new ComboBox<>();
            lDate = new Label("Data: ");
            fDate = new DatePicker();
            lStart = new Label("Godzina początku: ");
            fStart = new TextField();
            lEnd = new Label("Godzina końca: ");
            fEnd = new TextField();
            lLimit = new Label("Limit uczestników: ");
            fLimit = new Spinner<>(0, 100, 0);
            fPracownik.setItems(lPracowniks);
            fGymRoom.setItems(lRooms);
            lTitle.setAlignment(Pos.CENTER);

            GridPane.setColumnIndex(lTitle, 1);
            GridPane.setColumnSpan(lTitle, 2);
            GridPane.setColumnIndex(lName, 1);
            GridPane.setColumnIndex(lPracownik, 1);
            GridPane.setColumnIndex(lGymRoom, 1);
            GridPane.setColumnIndex(lDate, 1);
            GridPane.setColumnIndex(lStart, 1);
            GridPane.setColumnIndex(lEnd, 1);
            GridPane.setColumnIndex(lLimit, 1);

            GridPane.setColumnIndex(fName, 2);
            GridPane.setColumnIndex(fPracownik, 2);
            GridPane.setColumnIndex(fGymRoom, 2);
            GridPane.setColumnIndex(fDate, 2);
            GridPane.setColumnIndex(fStart, 2);
            GridPane.setColumnIndex(fEnd, 2);
            GridPane.setColumnIndex(fLimit, 2);
            GridPane.setColumnIndex(lSuccess, 1);
            GridPane.setColumnSpan(lSuccess, 2);

            GridPane.setColumnIndex(bAdd, 1);
            GridPane.setColumnIndex(bCancel, 2);

            GridPane.setRowIndex(lTitle, 0);
            GridPane.setRowIndex(lName, 1);
            GridPane.setRowIndex(lPracownik, 2);
            GridPane.setRowIndex(lGymRoom, 3);
            GridPane.setRowIndex(lDate, 4);
            GridPane.setRowIndex(lStart, 5);
            GridPane.setRowIndex(lEnd, 6);
            GridPane.setRowIndex(lLimit, 7);

            GridPane.setRowIndex(fName, 1);
            GridPane.setRowIndex(fPracownik, 2);
            GridPane.setRowIndex(fGymRoom, 3);
            GridPane.setRowIndex(fDate, 4);
            GridPane.setRowIndex(fStart, 5);
            GridPane.setRowIndex(fEnd, 6);
            GridPane.setRowIndex(fLimit, 7);

            GridPane.setRowIndex(lSuccess, 36);
            GridPane.setRowIndex(bAdd, 37);
            GridPane.setRowIndex(bCancel, 37);


            getChildren().addAll(lTitle, lName, fName, lPracownik, fPracownik, lGymRoom, fGymRoom, lDate, fDate, lStart, fStart, lEnd, fEnd, lLimit, fLimit, lSuccess, bAdd, bCancel);
        }

        protected void add()
            throws InvalidGymClassException{
            if(!validate())
                throw new InvalidGymClassException("Missing parameters");
            try{
                LocalDateTime start = LocalTime.parse(fStart.getText()).atDate(fDate.getValue());
                LocalDateTime end = LocalTime.parse(fEnd.getText()).atDate(fDate.getValue());
                new GymClass(fName.getText(), (Pracownik)OsobaModel.getModel().getByLogin(fPracownik.getValue()), start, end, fLimit.getValue(), fGymRoom.getValue());
            }
            catch(AddingGymClassException e){
                lSuccess.setText(e.getMessage());
                throw new InvalidGymClassException(e.getMessage());
            }
            catch(Exception e){
                e.printStackTrace();
                System.out.println(e);
            }
            
        }

        protected boolean validate(){
            boolean pass = true;
            if (fName.getText().length() < 1){
                lName.setTextFill(Color.TOMATO);
                pass = false;
            }
            else
                lName.setTextFill(Color.GREEN);
            if (fPracownik.getValue() == null){
                lPracownik.setTextFill(Color.TOMATO);
                pass = false;
            }
            else
                lPracownik.setTextFill(Color.GREEN);
            if (fGymRoom.getValue() == null){
                lGymRoom.setTextFill(Color.TOMATO);
                pass = false;
            }
            else
                lGymRoom.setTextFill(Color.GREEN);
            if (fDate.getValue() == null){
                lDate.setTextFill(Color.TOMATO);
                pass = false;
            }
            else
                lDate.setTextFill(Color.GREEN);
            if (fStart.getText().length() < 1){
                lStart.setTextFill(Color.TOMATO);
                pass = false;
            }
            else
                lStart.setTextFill(Color.GREEN);
            if (fEnd.getText().length() < 1){
                lEnd.setTextFill(Color.TOMATO);
                pass = false;
            }
            else
                lEnd.setTextFill(Color.GREEN);
                if (fEnd.getText().length() < 1){
                lEnd.setTextFill(Color.TOMATO);
                pass = false;
            }
            else
                lEnd.setTextFill(Color.GREEN);
            if (fLimit.getValue() < 1){
                lLimit.setTextFill(Color.TOMATO);
                pass = false;
            }
            else
                lLimit.setTextFill(Color.GREEN);
            return pass;
            
        }
}
class InvalidGymClassException
    extends Exception{
        public InvalidGymClassException(String msg){
            super(msg);
        }
    }