package com.pjatk_gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PracownikView 
    extends BorderPane{

        VBox leftPane;
        VBox rightPane;
        VBox centerPane;
        private Button bAddKlubowicz;
        private Button bAddGymClass;
        private Button bManageGymClasses;
        private Pracownik pracownik;
        private final int KLUBOWICZ = 0;

        public PracownikView(Pracownik p){
            super();
            this.pracownik = p;
            setupLeftPane();
            setupRightPane();
            setupCenterPane();
            setLeft(leftPane);
            setRight(rightPane);
            setCenter(centerPane);
        }

        private void setupRightPane(){
            rightPane = new VBox(5);
            Label title = new Label("Zajęcia");
            title.setStyle(App.TITLE_STAJL);
            bAddGymClass = new Button("Dodaj zajęcia");
            bAddGymClass.setOnAction(
                e -> addGymClass()
            );
            bManageGymClasses = new Button("Zarządzaj zajęciami");
            bManageGymClasses.setOnAction(
                e -> manageGymClasses()
            );
            rightPane.setAlignment(Pos.CENTER);
            rightPane.setStyle(App.BOX_STAJL);
            rightPane.getChildren().addAll(title, bAddGymClass, bManageGymClasses);
        }

        private void setupCenterPane(){
            centerPane = new VBox(5);
            centerPane.setStyle(App.BOX_STAJL);
            Label title = new Label("GloboGym");
            Label prompt = new Label("Może nie najtaniej, ale jako tako");
            prompt.setStyle(App.TITLE_STAJL);
            ImageView iV = new ImageView(App.icon);
            title.setStyle("-fx-font-size: 30px;\n" +
                "-fx-text-fill: orange");
            centerPane.setAlignment(Pos.CENTER);
            centerPane.getChildren().addAll(iV, title, prompt);
        }

        private void setupLeftPane(){
            leftPane = new VBox(5);
            Label title = new Label("Zasoby ludzkie");
            title.setStyle(App.TITLE_STAJL);
            bAddKlubowicz = new Button("Dodaj klubowicza");
            bAddKlubowicz.setOnAction(
                e -> addOsoba(KLUBOWICZ)
            );
            leftPane.setAlignment(Pos.CENTER);
            leftPane.setStyle(App.BOX_STAJL);

            leftPane.getChildren().addAll(title, bAddKlubowicz);
        }

        private void addOsoba(int type){
            Stage addingStage = new Stage();
            GridPane addingPane = new GridPane();
            switch (type) {
                case KLUBOWICZ:
                    addingPane = new AddingKlubowiczForm();
                    break;
                default:
                    break;
            }
            addingPane.setStyle(App.STAJL_STRING);
            Scene addingScene = new Scene(addingPane, 600, 400);
            addingStage.setTitle("Dodawanie");
            addingStage.setScene(addingScene);
            addingStage.getIcons().add(App.icon);
            addingStage.show();
        }

        private void addGymClass(){
            Stage addingStage = new Stage();
            GridPane addingPane = new AddingGymClassForm();
            addingPane.setStyle(App.STAJL_STRING);
            Scene addingScene = new Scene(addingPane, 350, 300);
            addingStage.setTitle("Dodawanie zajęć");
            addingStage.setScene(addingScene);
            addingStage.getIcons().add(App.icon);
            addingStage.show();
        }

        private void manageGymClasses(){
            Stage addingStage = new Stage();
            BorderPane addingPane = new ManagingGymClassForm(pracownik);
            addingPane.setStyle(App.STAJL_STRING);
            Scene addingScene = new Scene(addingPane, 600, 300);
            addingStage.setTitle("Zarządzanie zajęciami");
            addingStage.setScene(addingScene);
            addingStage.getIcons().add(App.icon);
            addingStage.show();
        }
}
