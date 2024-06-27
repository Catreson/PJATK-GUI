package com.pjatk_gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ShowingKlubowiczForm 
    extends BorderPane{

        private ObservableList<Osoba> lOsobas;
        private HBox topBox;
        private ListView<Osoba> listView;
        private Label lFilter;
        private TextField fFilter;
        private Button bSwitch;
        private boolean ifKlubowicz;
        

        public ShowingKlubowiczForm(){
            super();
            topBox = new HBox();
            lFilter = new Label("Szukana fraza: ");
            fFilter = new TextField();
            bSwitch = new Button("Pokaż pracowników");
            bSwitch.setOnAction(
                e -> switchDisp()
            );
            fFilter.setOnAction(
                e -> {
                    System.out.println("e");
                    filter();
                }
            );
            topBox.getChildren().addAll(bSwitch, lFilter, fFilter);
            ifKlubowicz = true;
            lOsobas = FXCollections.observableArrayList(ifKlubowicz ?
                OsobaModel.getModel().getKlubowiczs() :
                OsobaModel.getModel().getPracowniks());
            listView = new ListView<>();
            listView.setCellFactory(
                (ListView<Osoba> par) -> {
                    return new ListCell<>(){
                    Label fName = new Label();
                    Label fSurname = new Label();
                    Label fLogin = new Label();
                    HBox hB  = new HBox(fName, fSurname, fLogin);
                    @Override
                    protected void updateItem(Osoba o, boolean b){
                        super.updateItem(o, b);
                        if (b || o == null) {
                            setText(null);
                            setGraphic(null);
                        }
                        else if(o != null){
                            fName.setText(" "+o.getName());
                            fSurname.setText(" "+o.getSurname());
                            fLogin.setText(" " + o.getLogin());
                            setGraphic(hB);
                        }
                    }
                };
                }
            );
            listView.setItems(lOsobas);
            setCenter(listView);
            setTop(topBox);
        }

        private void filter(){
            String str = fFilter.getText();
            ObservableList<Osoba> lTMPOsobas = lOsobas.filtered( o -> o.getName().contains(str) || o.getSurname().contains(str));
            listView.setItems(lTMPOsobas);
            listView.refresh();
        }
        
        private void switchDisp(){
            ifKlubowicz = !ifKlubowicz;
            lOsobas = FXCollections.observableArrayList(ifKlubowicz ?
                OsobaModel.getModel().getKlubowiczs() :
                OsobaModel.getModel().getPracowniks());
            listView.setItems(lOsobas);
            listView.refresh();
            bSwitch.setText(ifKlubowicz ? 
                "Pokaż pracowników" :
                    "Pokaż klubowiczy");
        }
}