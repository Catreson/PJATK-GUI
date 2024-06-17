package com.w14;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ChoiceBoxListCell;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        //BorderPane root = new BorderPane();
        //scene = new Scene(root, 640, 480);

        /*ObservableList<String> list = FXCollections.observableArrayList("Red", "Green", "Blue");

        ListView<String> lV = new ListView<>(list);
        lV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lV.getSelectionModel().selectedIndexProperty().addListener(
            e -> System.out.println("Selected: " + lV.getSelectionModel().getSelectedItems())
        );
        lV.setEditable(true);
        ObservableList<String> itemList = FXCollections.observableArrayList(
            "Yellow", "Pink", "Black", "Orange"
        );

        /*lV.setCellFactory(
            ChoiceBoxListCell.forListView(itemList)
        );*/
        /*lV.setCellFactory(
            (ListView<String> par) -> {
                return new ListCell<>(){
                    Rectangle rec = new Rectangle(30, 30);
                    RadioButton rb1 = new RadioButton();
                    RadioButton rb2 = new RadioButton();
                    HBox hB = new HBox(rb1, rb2);
                    StackPane sP = new StackPane(rec, hB);
                    @Override
                    protected void updateItem(String s, boolean b){
                        if(s != null){
                            rec.setFill(Color.web(s));
                            setText(s);
                            setGraphic(sP);
                        }
                    }
                };
            }
        );
        root.setCenter(lV);
        /*lV.setCellFactory(
            TextFieldListCell.forListView(
                new IntegerStringConverter()
            )
        );
        Button b = new Button("Add");
        b.setOnAction(
            (e) -> list.add(""+(int)(Math.random()*100))
        );
        root.setBottom(b);*/
        /*TextField tF = new TextField();
        Button butt = new Button("Add");
        butt.setOnAction(
            e -> {
                try{
                    Color.web(tF.getText());
                    list.add(tF.getText());
                }catch(IllegalArgumentException ee){
                    System.out.println("Bad color");
                }
            }
        );
        FlowPane fP = new FlowPane(tF, butt);
        fP.setAlignment(Pos.CENTER);
        root.setBottom(fP);*/
        
        StackPane root = new StackPane();

        /*new Thread(){
            @Override
            public void run(){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                root.getChildren().add(new Text("test"));
            }
        }.start();*/
        
        Platform.runLater(
            () -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                root.getChildren().add(new Text("test"));
            }
        );

        stage.setScene(new Scene(root, 640, 480));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}