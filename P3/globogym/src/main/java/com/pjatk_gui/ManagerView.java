package com.pjatk_gui;

import java.time.LocalDate;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManagerView 
    extends BorderPane{

        VBox rightPane;
        VBox leftPane;
        VBox centerPane;
        private Button bAddPracownik;
        private Button bAddKlubowicz;
        private Button bAddManager;
        private Button bAddGymRoom;
        private Button bChangeQuote;
        private Button bShowKlubowiczs;
        private Button bShowGymClasses;
        private Button bStartSuper;
        private Button bStopSuper;
        private Manager manager;
        private final int PRACOWNIK = 1;
        private final int KLUBOWICZ = 0;
        private final int MANAGER = 2;

        public ManagerView(Manager m){
            super();
            this.manager = m;
            setupLeftPane();
            setupRightPane();
            setupCenterPane();
            setLeft(leftPane);
            setRight(rightPane);
            setCenter(centerPane);
        }

        private void setupCenterPane(){
            centerPane = new VBox(5);
            centerPane.setStyle(App.BOX_STAJL);
            centerPane.setAlignment(Pos.CENTER);
            Label title = new Label("Statystyki");
            title.setStyle(App.TITLE_STAJL);
            HBox hMeanDay = new HBox(3);
            Label lMeanDay = new Label("Średnia liczba klubowiczy na zajęciach: ");
            DatePicker fMeanDay = new DatePicker();
            Label vMeanDay = new Label();
            fMeanDay.setOnAction(
                e -> {
                    LocalDate selected = fMeanDay.getValue();
                    long denominator = GymClassModel.getModel().getClasses().stream().filter(gC -> gC.getDate().equals(selected)).count();
                    denominator = denominator == 0 ? 1 : denominator;
                    double mean = (double)GymClassModel.getModel().getClasses().stream().filter(gC -> gC.getDate().equals(selected)).map( gC -> gC.getNoKlubowiczs()).reduce(0, Integer::sum)
                        / denominator;
                    vMeanDay.setText(" "+mean);
                }
            );
            hMeanDay.getChildren().addAll(lMeanDay, fMeanDay, vMeanDay);

            HBox hRatio = new HBox(3);
            Label lRatio = new Label("Stosunek opłaconych do nieopłaconych: ");
            Label vRatio = new Label();
            long noValid = OsobaModel.getModel().getKlubowiczs().stream().filter( k -> k.verifyPass()).count();
            long noInvalid = OsobaModel.getModel().getKlubowiczs().stream().filter( k -> !k.verifyPass()).count();;
            vRatio.setText(String.format("%d/%d", noValid, noInvalid));
            hRatio.getChildren().addAll(lRatio, vRatio);

            HBox hMeanType = new HBox(3);
            Label lMeanType = new Label("Średnia zapisana na typ: ");
            Label vMeanType = new Label();
            ComboBox<String> fMeanType = new ComboBox<>();
            ObservableList<String> lTypes = FXCollections.observableArrayList(
                GymClassModel.getModel().getClasses().stream().map( gC -> gC.getName()).distinct().collect(Collectors.toList())
            );
            fMeanType.setItems(lTypes);
            fMeanType.setOnAction(
                e -> {
                    long nom;
                    long denom;
                    nom = GymClassModel.getModel().getClasses().stream().filter( gC -> gC.getName().equals(fMeanType.getValue())).map(gC -> gC.getNoKlubowiczs()).reduce(0, Integer::sum);
                    denom = GymClassModel.getModel().getClasses().stream().filter( gC -> gC.getName().equals(fMeanType.getValue())).count();
                    denom = denom == 0 ? 1 : denom;
                    vMeanType.setText("" + (double)nom/denom);
                }
            );
            hMeanType.getChildren().addAll(lMeanType, fMeanType, vMeanType);

            centerPane.getChildren().addAll(title ,hMeanDay, hRatio, hMeanType);
        }

        private void setupLeftPane(){
            leftPane = new VBox(5);
            Label title = new Label("Zasoby ludzkie");
            title.setStyle(App.TITLE_STAJL);
            bAddPracownik = new Button("Dodaj pracownika");
            bAddPracownik.setOnAction(
                e -> addOsoba(PRACOWNIK)
            );
            bAddKlubowicz = new Button("Dodaj klubowicza");
            bAddKlubowicz.setOnAction(
                e -> addOsoba(KLUBOWICZ)
            );
            bAddManager = new Button("Dodaj managera");
            bAddManager.setOnAction(
                e -> addOsoba(MANAGER)
            );
            bShowKlubowiczs = new Button("Pokaż załogę");
            bShowKlubowiczs.setOnAction(
                e -> showKlubowiczs()
            );
            leftPane.setAlignment(Pos.CENTER);
            leftPane.setStyle(App.BOX_STAJL);

            leftPane.getChildren().addAll(title, bAddManager, bAddPracownik, bAddKlubowicz, bShowKlubowiczs);
        }

        private void setupRightPane(){
            rightPane = new VBox(5);
            Label title = new Label("Zasoby rzeczowe");
            title.setStyle(App.TITLE_STAJL);
            bAddGymRoom = new Button("Dodaj salę");
            bAddGymRoom.setOnAction(
                e -> addGymRoom()
            );
            bChangeQuote = new Button("Ustaw cytat dnia");
            bChangeQuote.setOnAction(
                e -> changeQuote()
            );
            bShowGymClasses = new Button("Pokaż zajęcia");
            bShowGymClasses.setOnAction(
                e -> showGymClasses()
            );
            rightPane.setAlignment(Pos.CENTER);
            rightPane.setStyle(App.BOX_STAJL);
            bStartSuper = new Button("Zacznij podsłuch");
            bStopSuper = new Button("Zakończ podsłuch");
            bStartSuper.setOnAction(
                e -> Supervisor.getSupervisor().start()
            );
            bStopSuper.setOnAction(
                e -> showLogs()
            );

            rightPane.getChildren().addAll(title, bAddGymRoom, bChangeQuote, bShowGymClasses, bStartSuper, bStopSuper);
        }

        private void addOsoba(int type){
            Stage addingStage = new Stage();
            GridPane addingPane = new GridPane();
            switch (type) {
                case KLUBOWICZ:
                    addingPane = new AddingKlubowiczForm();
                    break;
                case PRACOWNIK:
                    addingPane = new AddingPracownikForm();
                    break;
                case MANAGER:
                    addingPane = new AddingManagerForm();
                    break;
                default:
                    break;
            }
            addingPane.setStyle(App.STAJL_STRING);
            Scene addingScene = new Scene(addingPane, 400, 300);
            addingStage.setTitle("Dodawanie");
            addingStage.setScene(addingScene);
            addingStage.getIcons().add(App.icon);
            addingStage.show();
        }

        private void addGymRoom(){
            Stage addingStage = new Stage();
            GridPane addingPane = new AddingGymRoomForm();
            addingPane.setStyle(App.STAJL_STRING);
            Scene addingScene = new Scene(addingPane, 200, 160);
            addingStage.setTitle("Dodawanie sali");
            addingStage.setScene(addingScene);
            addingStage.getIcons().add(App.icon);
            addingStage.show();
        }

        private void changeQuote(){
            Stage addingStage = new Stage();
            HBox addingPane = new HBox();
            addingPane.setStyle(App.STAJL_STRING);
            Scene addingScene = new Scene(addingPane, 200, 160);
            addingStage.setTitle("Zmiana cytatu");
            Button bChange = new Button("Ustaw");
            TextField fQuote = new TextField();
            bChange.setOnAction(
                e -> {  
                    Prices.getPrices().setQuote(fQuote.getText(), manager);
                    addingStage.hide();
                }
            );
            addingPane.getChildren().addAll(fQuote, bChange);

            addingStage.setScene(addingScene);
            addingStage.getIcons().add(App.icon);
            addingStage.showAndWait();
        }

        private void showLogs(){
            Stage addingStage = new Stage();
            ListView<String> addingPane = new ListView<>();
            addingPane.setItems(FXCollections.observableArrayList(Supervisor.getSupervisor().stop()));
            addingPane.setStyle(App.STAJL_STRING);
            Scene addingScene = new Scene(addingPane, 400, 600);
            addingStage.setTitle("Podgląd logów");
            addingStage.setScene(addingScene);
            addingStage.getIcons().add(App.icon);
            addingStage.show();
        }

        private void showKlubowiczs(){
            Stage addingStage = new Stage();
            BorderPane addingPane = new ShowingKlubowiczForm();
            addingPane.setStyle(App.STAJL_STRING);
            Scene addingScene = new Scene(addingPane, 400, 600);
            addingStage.setTitle("Podgląd załogi");
            addingStage.setScene(addingScene);
            addingStage.getIcons().add(App.icon);
            addingStage.show();
        }

        private void showGymClasses(){
            Stage addingStage = new Stage();
            BorderPane addingPane = new ShowingGymClassForm();
            addingPane.setStyle(App.STAJL_STRING);
            Scene addingScene = new Scene(addingPane, 500, 600);
            addingStage.setTitle("Podgląd zajęć");
            addingStage.setScene(addingScene);
            addingStage.getIcons().add(App.icon);
            addingStage.show();
        }
}
