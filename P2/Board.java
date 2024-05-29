import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board 
    extends JPanel
    implements KeyListener{
        private  final Font FONT1 = new Font(Font.MONOSPACED, Font.BOLD, 50);
        private  final Font FONT2 = new Font(Font.MONOSPACED, Font.BOLD, 50);
        private Ship player;
        private Bullet bullet;
        private Row row;
        private Bomb bomb;
        private Image iPlayer;
        private Image iRow;
        private Image iBullet;
        private Image iBomb;
        private Image iLife;
        private int fps = 60;
        private Timer timer;
        private long pTime = 0;
        private List<Row> lRows;
        private List<Bullet> lBullets;
        private List<Bomb> lBombs;
        public Board(Ship player, int noEnemies){
            setFocusable(true);
            addKeyListener(this);
            setSize(new Dimension(600, 800));
            setBackground(Color.black);
            this.lBullets = Bullet.getlBullets();
            this.lRows = Row.getLRows();
            this.lBombs = Bomb.getlBombs();
            this.player = player;
            this.bullet = new Bullet();
            this.row = new Row(noEnemies);
            this.bomb = new Bomb();
            this.iPlayer = player.getImage().getScaledInstance(getWidth()/12, getWidth()/12, Image.SCALE_DEFAULT);
            this.iBullet = bullet.getImage().getScaledInstance((int)(((double)bullet.getWidth()/Game.limX) * getWidth()), (int)(((double)bullet.getHeight()/Game.limX) * getWidth()), Image.SCALE_DEFAULT);
            this.iRow = row.getImage().getScaledInstance((int)(((double)row.getCellWidth() / Game.limX) * getWidth()), (int)(((double)row.getCellWidth() / Game.limX) * getWidth()), Image.SCALE_DEFAULT);
            this.iBomb = bomb.getImage().getScaledInstance((int)(((double)bomb.getWidth()/Game.limX) * getWidth()), (int)(((double)bomb.getHeight()/Game.limX) * getWidth()), Image.SCALE_DEFAULT);
            this.iLife = new ImageIcon("life.png").getImage().getScaledInstance(getWidth()/20, getWidth()/20, Image.SCALE_DEFAULT);

            timer = new Timer((int)(1000 / fps), new ActionListener() {
                @Override
                 public void actionPerformed(ActionEvent e) {
                    repaint();
                }
            });
            timer.start();
        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            if(Game.paused){
                g.setFont(FONT2);
                g.drawString("PAUSE", getWidth()/3, getHeight()/2);
            }
            if(!Game.over){
                g.setFont(FONT1);
                g.drawString(Scoreboard.getScore(), getWidth()/2, getHeight()/10);

                g.drawImage( iPlayer, xCoord(player.x), yCoord(player.y), this);
                for(int i = 0; i < Ship.getPlayer().getLives(); i++){
                    g.drawImage(iLife, (i * getWidth()/20), 0, this);
                }
                for(Bullet b : lBullets){
                    g.drawImage( iBullet, xCoord(b.x), yCoord(b.y), this);
                }
                for(Bomb b : lBombs){
                    g.drawImage( iBomb, xCoord(b.x), yCoord(b.y), this);
                }
                for(Row r : lRows){
                    drawRow(g, r);
                }
            }
            else{
                    g.setFont(FONT2);
                    g.drawString("GAME OVER", getWidth()/4, getHeight()/2);
                }
            }

        public int xCoord(int x){
            return x * getWidth() / Game.limX;
        }

        public int yCoord(int y){
            int yLim = getHeight();
            return yLim - (y * yLim / Game.limY);
        }

        private void drawRow(Graphics g, Row r){
            List<Alien> lI = r.getPositionIndices();
            for(Alien a : lI){
                if(a.isAlive())
                    g.drawImage( iRow, xCoord(r.x + a.getStart()), yCoord(r.y), this);
            }
            
        }

        public void stop(){
            timer.stop();
            repaint();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(e);
            System.out.println("1");
        }
    
        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println(e);
            System.out.println("2");
        }
    
        @Override
        public void keyTyped(KeyEvent e) {
            System.out.println(e);
            System.out.println("3");
        }

}
