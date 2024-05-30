import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Bomb 
    extends AObject{
        private static ArrayList<Bomb> lBombs = new ArrayList<>();

        private boolean active = true;
        private static Image image;
        private int height;
        private Ship player;
        private double multiplier;
        private double hardcore;

        public Bomb(){
            super();
            image = new ImageIcon(GameParameters.getPrefix() + "bomb.png").getImage();
            setDim();
        }
        public Bomb(int x, int y, double vx, double vy, double ax, double ay){
            super(x, y, vx, vy, ax, ay);
            image = new ImageIcon(GameParameters.getPrefix() + "bomb.png").getImage();
            setDim();
            lBombs.add(this);
            player = Ship.getPlayer();
            multiplier = GameParameters.getPar("hardcoreMultiplier");
            hardcore = GameParameters.getPar("hardcore");
        }

        private void setDim(){
            this.width = Game.limX / 40;
            this.height = Game.limX / 40;
        }

        @Override
        public void move(double tick){
            if(hardcore==1){
                vx = multiplier * ((double)Ship.getPlayer().x - x)/Game.limX * (1 - (y - (double)Ship.getPlayer().y)/Game.limY);
            }
            super.move(tick);
            if(y < 0)
                active = false;
            if(y <= player.y && x  >= player.x && x <= player.x + player.getWidth() && active){
                player.hit();
                active = false;
            }
        }

        public boolean isActive(){
            return active;
        }

        public Image getImage() {
            return image;
        }

        public int getHeight() {
            return height;
        }

        public static ArrayList<Bomb> getlBombs() {
            return lBombs;
        }
}