import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Bullet 
    extends AObject{
        private static ArrayList<Bullet> lBullets = new ArrayList<>();

        private boolean active = true;
        private Image image;
        private int height;

        public Bullet(){
            super();
            this.image = new ImageIcon("bullet.jpg").getImage();
            setDim();
        }
        public Bullet(int x, int y, double vx, double vy, double ax, double ay){
            super(x, y, vx, vy, ax, ay);
            this.image = new ImageIcon("bullet.jpg").getImage();
            setDim();
            lBullets.add(this);
        }

        private void setDim(){
            this.width = Game.limX / 80;
            this.height = Game.limX / 40;
        }

        @Override
        public void move(double tick){
            super.move(tick);
            if(y > Game.limY)
                active = false;
            ArrayList<Row> lR = new ArrayList<>(Row.getLRows());
            for(Row r : lR){
                if(r.intersects(this)){
                    System.out.println("HIT");
                    active = false;
                }
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

        public static ArrayList<Bullet> getlBullets() {
            return lBullets;
        }
}
