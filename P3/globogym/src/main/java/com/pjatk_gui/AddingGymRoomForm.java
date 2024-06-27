package com.pjatk_gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class AddingGymRoomForm 
    extends GridPane{
        protected Button bAdd;
        protected Button bCancel;

        protected Label lTitle;
        protected Label lSuccess;
        protected Label lId;
        protected Label lName;
        protected TextField fId;
        protected TextField fName;

        public AddingGymRoomForm(){
            super();
            
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
                    } catch (InvalidGymRoomException ee) {
                        lSuccess.setText(ee.getMessage());
                        lSuccess.setTextFill(Color.TOMATO);
                    }
                }
            );

            bCancel.setOnAction(
                e -> getScene().getWindow().hide()
            );

            lTitle = new Label("Dodawanie sali");
            lTitle.setStyle(App.TITLE_STAJL);
            lSuccess = new Label();
            lName = new Label("Nazwa: ");
            fName = new TextField();
            lId = new Label("ID sali: ");
            fId = new TextField("0");
            fId.textProperty().addListener(new ChangeListener<String>() {
            @Override
             public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*") || newValue.equals("")) {
                    fId.setText(oldValue);
                }
            }
            });

            GridPane.setColumnIndex(lTitle, 1);
            GridPane.setColumnSpan(lTitle, 2);
            GridPane.setColumnIndex(lName, 1);
            GridPane.setColumnIndex(lId, 1);


            GridPane.setColumnIndex(fName, 2);
            GridPane.setColumnIndex(fId, 2);
            GridPane.setColumnIndex(lSuccess, 1);
            GridPane.setColumnSpan(lSuccess, 2);

            GridPane.setColumnIndex(bAdd, 1);
            GridPane.setColumnIndex(bCancel, 2);

            GridPane.setRowIndex(lTitle, 0);
            GridPane.setRowIndex(lName, 1);
            GridPane.setRowIndex(lId, 2);

            GridPane.setRowIndex(fName, 1);
            GridPane.setRowIndex(fId, 2);

            GridPane.setRowIndex(lSuccess, 36);
            GridPane.setRowIndex(bAdd, 37);
            GridPane.setRowIndex(bCancel, 37);


            getChildren().addAll(lTitle, lName, fName, fId, lId, lSuccess, bAdd, bCancel);
        }

        protected void add()
            throws InvalidGymRoomException{
            if(!validate())
                throw new InvalidGymRoomException("Missing parameters");
            try {
                new GymRoom(Integer.parseInt(fId.getText()), fName.getText());
            } catch (RoomDuplicateException e) {
                    throw new InvalidGymRoomException("Room duplicate");
            } catch (Exception e){
                System.out.println(e);
                e.printStackTrace();
                throw new InvalidGymRoomException(e.getMessage());
            }
            System.out.println("ADDED");
        }

        protected boolean validate(){
            boolean pass = true;
            if (fName.getText().length() < 1){
                lName.setTextFill(Color.TOMATO);
                pass = false;
            }
            else
                lName.setTextFill(Color.GREEN);
            if (Integer.parseInt(fId.getText()) < 1) {
                lId.setTextFill(Color.TOMATO);
                pass = false;
            }
            else
                lId.setTextFill(Color.GREEN);
            return pass;
            
        }
}
class InvalidGymRoomException
    extends Exception{
        public InvalidGymRoomException(String msg){
            super(msg);
        }
    }