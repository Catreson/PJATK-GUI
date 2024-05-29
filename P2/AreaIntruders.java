import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class AreaIntruders 
implements Runnable{
    private JFrame mainFrame;
    private StartPanel setupPanel;
    private Board board;
    private Game game;
    private Scoreboard sboard;
    private JPanel gamePanel;
    LoadingPanel lP;
    public AreaIntruders(){
        mainFrame = new JFrame("Area Invaders");
        mainFrame.setSize(1200, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setIconImage(new ImageIcon("bomba.jpg").getImage());
        lP = new LoadingPanel(10);
        this.game = new Game(10, 5, 60);
        board = game.getBoard();
        sboard = new Scoreboard();
        gamePanel = new JPanel();
        mainFrame.add(lP);
        mainFrame.pack();
        try {
            lP.tred.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mainFrame.remove(lP);
        mainFrame.setSize(600, 800);
        mainFrame.add(board);
    }   

    public void run() {
    }

    
}
