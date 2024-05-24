import java.util.ArrayList;

public class Game {
    public static final int lim_x = 3000;
    public static final int lim_y = 5000;
    public static final int stepSize = 200;
    private int enemySpeed = 1;
    public static final double tick = 0.01;
    private ArrayList<Row> lRows;
    private ArrayList<Bullet> lBullets;
    private Ship player;
    private int score = 0;





    public void onTick(){
        for(AObject o : lRows){
            o.move(tick*enemySpeed);
        }
        for(AObject o : lBullets){
            o.move(tick);
        }
    }

    public void onArrowPressed(int dir){
        player.x += dir * stepSize;
    }

    public void onSpacePressed(){
        Bullet tmp = new Bullet(player.getCenter(), 0, 0, 100, 0, -4);
        lBullets.add(tmp);
    }

    public void killedEnemy(){
        score++;
        enemySpeed++;
    }

    public int getScore(){
        return score;
    }

}
