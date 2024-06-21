package com.pjatk_gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalDate;

public class App 
    extends Application {

    public static final String STAJL_STRING = "-fx-base: rgba(0, 1, 2, 255);";
    public static Image icon;
    private static Scene scene;
    
    private Osoba user;

    @Override
    public void start(Stage stage) throws IOException {
        icon = new Image(App.class.getResource("gg1.jpg").toString(), 50, 50, true, false);
        Password pwd = null;
        try {
            pwd = PasswordManager.getPasswordManager().createPassword("admin".toCharArray(), 10000);
        } catch (PasswordTooShortException e) {
            System.out.println(e);
        }
        try {
            Manager admin = new Manager("admin", pwd, "admin", "admin", LocalDate.now(), 0, "admin");
        } catch (LoginDuplicateException e) {
            System.out.println(e);
        }
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
        Scene scene = new Scene(vB, 640, 480);

        if(user instanceof Klubowicz){
            Tab klubowiczTab = new Tab("Klubowicz", new KlubowiczView());
            klubowiczTab.setClosable(false);
            tP.getTabs().add(klubowiczTab);
        }
        if(user instanceof Pracownik){
            Tab pracownikTab = new Tab("Pracownik", new PracownikView());
            pracownikTab.setClosable(false);
            tP.getTabs().add(pracownikTab);
        }
        if(user instanceof Manager){
            Tab managerTab = new Tab("Manger", new ManagerView());
            managerTab.setClosable(false);
            tP.getTabs().add(managerTab);
        }
        Tab osobaTab = new Tab("Profil", new ProfileView(user));
        osobaTab.setClosable(false);
        tP.getTabs().add(osobaTab);
        vB.setStyle("-fx-base: rgba(0, 1, 2, 255);");
        stage.setScene(scene);
        if(user != null)
            stage.show();
        stage.setTitle("GloboGym");
        stage.getIcons().add(new Image(App.class.getResource("gg1.jpg").toString(), 50, 50, true, false));
        System.out.println("e");
    }


    public static void main(String[] args) {
        launch();
    }

    public void setOsoba(Osoba o){
        this.user = o;
    }

}