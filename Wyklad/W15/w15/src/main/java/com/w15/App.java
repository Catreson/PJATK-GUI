package com.w15;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Camera;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.effect.Reflection;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Pane root = new Pane();

        Rectangle rectangle = new Rectangle(0, 0, 150, 150);

        Stop[] stops = new Stop[]{
            new Stop(0, Color.RED),
            new Stop(0.33, Color.DEEPPINK),
            new Stop(0.66, Color.GREEN),
            new Stop(1, Color.BLUE),
        };

        LinearGradient lG = new LinearGradient(0, 0, 100, 100, false, CycleMethod.REPEAT, stops);

        Reflection reflection = new Reflection();

        SepiaTone sepiaTone = new SepiaTone();

        rectangle.setFill(lG);

        PhongMaterial pM = new PhongMaterial();
        pM.setDiffuseColor(Color.RED);
        Image bM = new Image("https://upload.wikimedia.org/wikipedia/commons/8/86/IntP_Brick_NormalMap.png");
        pM.setBumpMap(
            bM
        );

        ArrayList<Box> boxes = new ArrayList<>();
        Box box = new Box(100, 100, 100);
        Indice ind = new Indice(boxes);



        boxes.add(box);

        box.setMaterial(pM);

        AmbientLight ambientLight = new AmbientLight(
            Color.web("0x404040")
        );

        PointLight pointLight = new PointLight(
            Color.web("0xFFCC33")
        );

        pointLight.setTranslateX(400);
        pointLight.setTranslateY(300);
        pointLight.setTranslateZ(-100);

        /*box.setTranslateX(110);
        box.setTranslateY(100);
        box.setTranslateZ(-50);*/

        box.setRotate(55);
        box.setRotationAxis(new Point3D(150, 150, 0));


        root.getChildren().addAll(box, ambientLight, pointLight);

        /*root.getChildren().addAll(
            new Circle(180, 50, 70, Color.rgb(255, 0, 0, 0.5)),
            new Circle(130, 150, 70, Color.rgb(0, 255, 0, 0.5)),
            new Circle(230, 150, 70, Color.rgb(0, 0, 255, 0.5))
        );*/

        Camera camera = new PerspectiveCamera(false);
        camera.setTranslateX(0);
        camera.setTranslateY(0);
        camera.setTranslateZ(-50);
        Scene scene = new Scene(root, 320, 280);
        scene.setCamera(camera);
        scene.setOnKeyPressed(
            e -> {
                switch (e.getCode()) {
                    case Q:
                        boxes.get(ind.get()).setRotate(boxes.get(ind.get()).getRotate() + 10);
                        break;
                    case E:
                        boxes.get(ind.get()).setRotate(boxes.get(ind.get()).getRotate() - 10);
                        break;
                    case W:
                        boxes.get(ind.get()).setTranslateY(boxes.get(ind.get()).getTranslateY() - 5);
                        break;
                    case S:
                        boxes.get(ind.get()).setTranslateY(boxes.get(ind.get()).getTranslateY() + 5);
                        break;
                    case D:
                        boxes.get(ind.get()).setTranslateX(boxes.get(ind.get()).getTranslateX() + 5);
                        break;
                    case A:
                        boxes.get(ind.get()).setTranslateX(boxes.get(ind.get()).getTranslateX() - 5);
                        break;
                    case B:
                        Box tmp = new Box(100, 100, 100);
                        PhongMaterial tpM = new PhongMaterial();
                        tpM.setBumpMap(bM);
                        tpM.setDiffuseColor(Color.rgb((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
                        tmp.setMaterial(tpM);
                        boxes.add(tmp);
                        tmp.setRotate(55);
                        tmp.setRotationAxis(new Point3D(150, 150, 0));
                        ind.increment();
                        root.getChildren().add(tmp);
                        break;
                    default:
                        break;
                }
            }
        );

        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}

class Indice{
    int indice = 0;
    List<?> l;
    public Indice(List<?> l){
        this.l = l;
    }

    public int get(){
        return indice;
    }

    public void increment(){
        indice = indice < l.size() - 1 ? indice + 1 : indice;
    }

    public void decrement(){
        indice = indice > 0 ? indice - 1 : indice;
    }
}