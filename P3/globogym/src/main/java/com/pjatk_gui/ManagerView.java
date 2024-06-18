package com.pjatk_gui;

import java.util.Stack;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ManagerView 
    extends BorderPane{

        VBox leftPane;
        private Button bAddPracownik;
        private Button bAddKlubowicz;
        private final int PRACOWNIK = 1;
        private final int KLUBOWICZ = 0;
        private final int MANAGER = 2;

        public ManagerView(){
            super();
            setupLeftPane();
            setLeft(leftPane);
        }

        private void setupLeftPane(){
            leftPane = new VBox(5);
            Text title = new Text("Zasoby ludzkie");
            title.setStyle("-fx-font-size: 16px;\n" +
                "-fx-fill: orange");
            bAddPracownik = new Button("Dodaj pracownika");
            bAddKlubowicz = new Button("Dodaj klubowicza");
            bAddKlubowicz.setOnAction(
                e -> addOsoba(KLUBOWICZ)
            );
            leftPane.setAlignment(Pos.CENTER);
            leftPane.setStyle("-fx-border-color: orange;\n" +
                   "-fx-border-insets: 10;\n" +
                   "-fx-hgap: 10px;\n" +
                   "-fx-padding: 2px;\n" +
                   "-fx-border-width: 1;\n" +
                   "-fx-border-radius: 5px;\n" +
                   "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);\n"
                   );

            leftPane.getChildren().addAll(title, bAddPracownik, bAddKlubowicz);
        }

        private void addOsoba(int type){
            Stage addingStage = new Stage();
            GridPane addingPane = new GridPane();
            switch (type) {
                case KLUBOWICZ:
                    addingPane = new AddingKlubowiczPane();
                    break;
            
                default:
                    break;
            }
            addingPane.setStyle(App.STAJL_STRING);
            Scene addingScene = new Scene(addingPane, 600, 600);
            addingStage.setTitle("Dodawanie");
            addingStage.setScene(addingScene);
            addingStage.getIcons().add(App.icon);
            addingStage.show();
        }
}
