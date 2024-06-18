package com.pjatk_gui;

import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class OsobaView   
    extends BorderPane{

        


        public OsobaView(Osoba user){
            super();
            ImageView imageView = new ImageView(user.getProfilePic());
            setCenter(imageView);
        }

}
