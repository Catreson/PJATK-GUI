import java.awt.Image;
import java.util.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Row 
    extends AObject{
    private static ArrayList<Row> lRows = new ArrayList<>();
    private int width;
    private int cellWidth;
    private int noEnemies;
    private ArrayList<Alien> positionsIndices;
    private Image image;
    private static double alienSpeed = 1;
    private double gravity;

    public Row(int noEnemies){
        super();
        this.width = (int)(Game.limX * 2 / 3.0);
        this.noEnemies = noEnemies;
        cellWidth = (int)(2.0/3*width/noEnemies);
        this.image = new ImageIcon(GameParameters.getPrefix() + "alien.png").getImage();
    }

    public Row(double vx, int vy, int noEnemies) {
        super(0, 0, vx, vy);
        this.width = (int)(Game.limX * 2 / 3.0);
        this.noEnemies = noEnemies;
        cellWidth = (int)(2.0/3*width/noEnemies);
        this.y = 5000 - lRows.size()*cellWidth;
        positionsIndices = new ArrayList<>();
        setPositionIndices();
        this.image = new ImageIcon(GameParameters.getPrefix() + "alien.png").getImage();
        System.out.println("New row" + y);
        gravity = GameParameters.getPar("gravity");
        lRows.add(this);
    }
        
    public Image getImage(){
        return this.image;
    }

    private void setPositionIndices(){
        int windowWidth = width / noEnemies;
        for(int i = 0; i < noEnemies; i++){
            positionsIndices.add(new Alien( (windowWidth / 6) + i * windowWidth , cellWidth + (windowWidth / 6) + i * windowWidth));
        }
    }

    public void bomb(int start){
        new Bomb(x + start, y, (0.2 * (Math.random() - 0.5)), -0.5, 0, gravity);
    }

    @Override
    public void move(double tick){
        x += tick * vx * Row.alienSpeed;
        vx += ax * tick;
        if(positionsIndices.isEmpty()){
            return;
        }
        if(x + positionsIndices.getFirst().getStart() < 0 || x + positionsIndices.getLast().getEnd() > Game.limX){
            int tmp = x;
            for(Row r : lRows){
                r.x = tmp;
                r.vx = -r.vx;
                r.y -= cellWidth;
            }
        }
        if(y < Ship.limY){
            Game.stopAll();
        }
    }

    public List<Alien> getPositionIndices(){
        return positionsIndices;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public static List<Row> getLRows(){
        return lRows;
    }

    private void kill(Alien a){
        positionsIndices.remove(a);
        a.kill();
        if(positionsIndices.isEmpty()){
            System.out.println("Row eliminated");
            Row.lRows.remove(this);
        }
        if(lRows.isEmpty()){
            System.out.println("Victory");
            alienSpeed = 1;
            Game.newRound();
        }
    }

    public boolean intersects(Bullet b){
        if((b.y >= y - cellWidth && b.y - b.getHeight()<= y) && (b.x + b.getWidth() >= x && b.x <= x+width)){
            for(Alien a : positionsIndices){
                if(a.isAlive() && b.x + b.getWidth() >= x + a.getStart() && b.x <= x + a.getEnd()){
                    kill(a);
                    return true;
                }
            }
        }
        return false;
    }

    public static void incrementAlienSpeed(){
        alienSpeed+=0.02;
    }
}
