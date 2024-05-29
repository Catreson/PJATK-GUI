import java.awt.Image;

import javax.swing.ImageIcon;

public class Ship 
    extends AObject{
    private Image view;
    public static int limY;
    private static Ship player;

    private int lives;
    public int getLives() {
        return lives;
    }

    public Ship(int x, int y, int lives) {
        super(x, y);
        limY = y;
        this.lives = lives;
        view = new ImageIcon("bomba.png").getImage();
        this.width = Game.limX/20;
        player = this;
    }

    public Image getImage(){
        return view;
    }

    @Override
    public void move(double tick){
        super.move(tick);
        if(x < 0 || x + width > Game.limX){
            vx = -vx;
            x = x < 0 ? 0 : Game.limX - width;
        }
    }

    public static Ship getPlayer() {
        return player;
    }

    public void hit(){
        System.out.println("TRAFIONY");
        lives--;
        if(lives <= 0){
            Game.stopAll();
        }
    }
}
