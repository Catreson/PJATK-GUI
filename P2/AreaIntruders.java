import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;



public class AreaIntruders 
implements Runnable{
    private JFrame mainFrame;

    private StartPanel setupPanel;
    private Board board;
    private Game game;
    private Scoreboard sboard;
    private JPanel gamePanel;
    private JPanel welcomePanel;
    private JMenuBar mBar;
    private String name;
    LoadingPanel lP;
    public AreaIntruders(){
        setup();
        startMenu();
        //loading();

        //startGame();
    }   

    public void run() {
    }

    private void setup(){
        mBar = new JMenuBar();
        mainFrame = new JFrame("Area Invaders");
        name = "Bomba";
        JMenu setings = new JMenu("Settings");
        JMenuItem setup = new JMenuItem("Setup");
        setings.add(setup);
        mBar.add(setings);
        mainFrame.setJMenuBar(mBar);
        
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setIconImage(new ImageIcon("bomba.jpg").getImage());
        mainFrame.setSize(1200, 800);
        setupPanel = new StartPanel(this);
        sboard = new Scoreboard();
    }

    public void loading(){
        lP = new LoadingPanel(10);
        mainFrame.add(lP);
        mainFrame.pack();
        try {
            lP.tred.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mainFrame.remove(lP);
    }

    public void startGame(){
        mainFrame.remove(welcomePanel);
        this.game = new Game(10, 5, 60);
        board = game.getBoard();
        mainFrame.setSize(600, 800);
        mainFrame.add(board);
    }

    private void startMenu(){
        welcomePanel = new JPanel();
        JPanel img = new JPanel();
        JLabel imag = new JLabel(new ImageIcon("bomba.jpg"));
        img.add(imag);
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.add(img);
        welcomePanel.add(setupPanel);
        
        mainFrame.add(welcomePanel);
        mainFrame.pack();
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public void setName(String name){
        this.name = name;
        System.out.println(this.name);
    }
}
