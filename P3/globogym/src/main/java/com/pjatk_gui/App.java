package com.pjatk_gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;

public class App 
    extends Application {

    public static final String STAJL_STRING = "-fx-base: rgba(0, 1, 2, 255);";
    public static final String BOX_STAJL = "-fx-border-color: orange;\n" +
                   "-fx-border-insets: 10;\n" +
                   "-fx-hgap: 10px;\n" +
                   "-fx-padding: 2px;\n" +
                   "-fx-border-width: 1;\n" +
                   "-fx-border-radius: 5px;\n" +
                   "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);\n";
    public static final String TITLE_STAJL = "-fx-font-size: 16px;\n" +
                "-fx-text-fill: orange";
    public static Image icon;
    
    private Osoba user;

    @Override
    public void start(Stage stage) throws IOException {
        icon = new Image(App.class.getResource("gg1.jpg").toString(), 100, 100, true, false);
        Prices.getPrices();
        GymClassModel.getModel();
        GymRoomModel.getModel();
        OsobaModel.getModel();

        addAdmin();
        
        LoginView loginView = new LoginView();
        Stage loginStage = new Stage(){
            @Override
            public void showAndWait(){
                super.showAndWait();
                user = ((LoginView)getScene().getRoot()).getOsoba();
            }
        };
        loginStage.setScene(loginView.getScene());
        loginStage.setResizable(false);
        loginStage.initStyle(StageStyle.UNDECORATED);
        loginView.setStyle(STAJL_STRING);
        loginStage.getIcons().add(icon);
        loginStage.showAndWait();

        TabPane tP = new TabPane();
        VBox vB = new VBox(tP);
        Scene scene = new Scene(vB, 740, 480);
        Label lQuote = new Label(Prices.getPrices().getQuote());
        lQuote.setStyle("-fx-font-size: 24px;\n" +
                        "-fx-text-fill: orange;\n" +
                        "-fx-font-style: italic");
        BorderPane g = new BorderPane();
        Scene quoteScene = new Scene(g, 400, 200);
        lQuote.setAlignment(Pos.CENTER);
        g.setCenter(lQuote);
        vB.setStyle(STAJL_STRING);
        g.setStyle(STAJL_STRING);
        
        if(user instanceof Klubowicz){
            Tab klubowiczTab = new Tab("Klubowicz", new KlubowiczView((Klubowicz)user));
            klubowiczTab.setClosable(false);
            tP.getTabs().add(klubowiczTab);
        }
        if(user instanceof Pracownik){
            Tab pracownikTab = new Tab("Pracownik", new PracownikView((Pracownik)user));
            pracownikTab.setClosable(false);
            tP.getTabs().add(pracownikTab);
        }
        if(user instanceof Manager){
            Tab managerTab = new Tab("Manger", new ManagerView((Manager)user));
            managerTab.setClosable(false);
            tP.getTabs().add(managerTab);
        }
        Tab osobaTab = new Tab("Profil", new ProfileView(user));
        osobaTab.setClosable(false);
        tP.getTabs().add(osobaTab);
        if(user != null)
            stage.show();
        stage.setTitle("GloboGym");
        stage.getIcons().add(icon);
        System.out.println("e");
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0),
                e -> stage.setScene(quoteScene)),
            new KeyFrame(Duration.seconds(3), 
                e -> stage.setScene(scene))
        );
        timeline.playFromStart();
    }


    public static void main(String[] args) {
        launch();
    }

    public void setOsoba(Osoba o){
        this.user = o;
    }

    private void addAdmin(){
        Password pwd = null;
        try {
            pwd = PasswordManager.getPasswordManager().createPassword("admin".toCharArray(), 10000);
        } catch (PasswordTooShortException e) {
            System.out.println(e);
        }
        try {
            new Manager("admin", pwd, "admin", "admin", LocalDate.now(), 0, "admin");
        } catch (LoginDuplicateException e) {
            System.out.println(e);
        }
    }

}