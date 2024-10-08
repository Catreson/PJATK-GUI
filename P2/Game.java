import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class Game{
    private static Game currentGame;
    public static final int limX = 3000;
    public static final int limY = 5000;
    public static final int stepSize = 20;
    private final int noEnemies;
    private final int noRows;
    private double tick = 0.01;
    private ArrayList<Bullet> deadBullets;
    private ArrayList<Bomb> deadBombs;
    private Ship player;
    private int score = 0;
    private Board board;
    private Integer pRight = 1;
    private Integer pLeft = -1;
    private Integer pSpace = 0;
    private Integer rRight = 2;
    private Integer rLeft = -2;
    private Integer pause = 3;

    private long pTime = 0;
    private Timer timer;
    private int cnt = 0;
    private int target = 120;
    private int resolution = 600;
    public static boolean over;
    public static boolean paused = false;
    private double bombCooldownMultiplier;
    private double gunCooldown;
    private double hardcore;
    private double gravity;
    private AreaIntruders parent;
    private double initialSpeed;

    public Game(AreaIntruders parent){
        over = false;
        paused = false;
        Scoreboard.reset();
        Row.getLRows().clear();
        Bomb.getlBombs().clear();
        Bullet.getlBullets().clear();
        this.parent = parent;
        bombCooldownMultiplier = GameParameters.getPar("bombCooldownMultiplier");
        gunCooldown = GameParameters.getPar("gunCooldown");
        hardcore = GameParameters.getPar("hardcore");
        gravity = GameParameters.getPar("gravity");
        this.resolution = (int)GameParameters.getPar("resolution");
        this.tick = 1000.0/resolution;
        this.noEnemies = (int)GameParameters.getPar("noEnemies");
        this.noRows = (int)GameParameters.getPar("noRows");
        this.initialSpeed = GameParameters.getPar("initialAlienSpeed");
        this.player = new Ship(0, 300, (int)GameParameters.getPar("noLife"));

        this.deadBullets = new ArrayList<>();
        this.deadBombs = new ArrayList<>();
        round();
        this.board = new Board(player, noEnemies);
        board.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed RIGHT"), pRight);
        board.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed P"), pause);
        board.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released RIGHT"), rRight);
        board.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed LEFT"), pLeft);
        board.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released LEFT"), rLeft);
        board.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed SPACE"), pSpace);
        board.getActionMap().put(pRight, new KeyAction(pRight));
        board.getActionMap().put(rRight, new KeyAction(rRight));
        board.getActionMap().put(pLeft, new KeyAction(pLeft));
        board.getActionMap().put(rLeft, new KeyAction(rLeft));
        board.getActionMap().put(pSpace, new KeyAction(pSpace));
        board.getActionMap().put(pause, new KeyAction(pause));
        currentGame = this;

        timer = new Timer((int)(tick), new ActionListener() {
            @Override
             public void actionPerformed(ActionEvent e) {
                onTick();
            }
        });
        timer.start();
    }



    public void onTick(){
        for(Row r : Row.getLRows()){
            r.move(tick);
        }
        for(Bullet b : Bullet.getlBullets()){
            if(b.isActive())
                b.move(tick);
            else
                deadBullets.add(b);
        }
        for(Bomb b : Bomb.getlBombs()){
            if(b.isActive())
                b.move(tick);
            else
                deadBombs.add(b);
        }
        Bullet.getlBullets().removeAll(deadBullets);
        Bomb.getlBombs().removeAll(deadBombs);
        player.move(tick);
        if(cnt >= target){
            target = (int)(bombCooldownMultiplier * Math.random() * (resolution));
            bomb();
            cnt = 0;
        }
        cnt++;
    }

    private void bomb(){
        List<Row> lR = Row.getLRows();
        int y = (int)(1 + Math.random() * lR.size()) - 1;
        Row r = lR.get(y);
        List<Alien> lA = r.getPositionIndices();
        int x = (int)(1 + Math.random() * lA.size()) - 1;
        Alien a = lA.get(x);
        r.bomb(a.getStart());
    }

    public void onArrowPressed(int dir){
        player.ax = 0.01 * dir;
    }

    public void onButtonPressed(int dir){
        player.vx = 0;
        player.x += dir * 30;
    }

    public void onArrowReleased(int dir){
        player.ax = 0;
    }

    public void onSpacePressed(){
        if(System.currentTimeMillis() - pTime > gunCooldown){
            new Bullet(player.getCenter(), player.y, (hardcore * (3 * (Math.random() - 0.5) + player.vx)), 6, 0, gravity);
            pTime = System.currentTimeMillis();
        }
    }


    public int getScore(){
        return score;
    }

    public Board getBoard(){
        return board;
    }
    private class KeyAction 
        extends AbstractAction{
            private int dir;
            private KeyAction(int dir){
                this.dir = dir;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (dir) {
                    case 1:
                        onArrowPressed(1);
                        break;
                    case 2:
                        onArrowReleased(1);
                        break;
                    case -1:
                        onArrowPressed(-1);
                        break;
                    case -2:
                        onArrowReleased(-1);
                        break;
                    case 0:
                        onSpacePressed();
                        break;
                    case 3:
                        pause();
                        break;
                }
        }
    }

    private void end(){
        System.out.println("Game over");
        timer.stop();
        over = true;
        Thread t =new Thread(){
            @Override
            public void run(){
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                board.stop();
                parent.endGame();
            }
        };
        t.start();
    }

    private void pause(){
        if(paused){
            timer.start();
            paused = false;
        }
        else{
            timer.stop();
            paused = true;
        }
    }

    private void round(){
        System.out.println("Next round");
        for(int i = 0; i < noRows; i++){
            new Row(initialSpeed, 0, noEnemies);
        }
    }

    public static void stopAll(){
        currentGame.end();
    }

    public static void newRound(){
        currentGame.round();
    }

}
