import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BoxLayout;


public class AreaIntruders 
implements Runnable{
    private JFrame mainFrame;
    private StartPanel setupPanel;
    private Board board;
    LoadingPanel lP;
    public AreaIntruders(){
        mainFrame = new JFrame("Area Invaders");
        mainFrame.setSize(1200, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupPanel = new StartPanel();
        board = new Board();
        mainFrame.setVisible(true);
        lP = new LoadingPanel(3);
        /*mainFrame.add(lP, BorderLayout.PAGE_START);
        //mainFrame.add(setupPanel, BorderLayout.PAGE_END);
        mainFrame.pack();
        try {
            lP.tred.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mainFrame.removeAll();*/
        mainFrame.add(board);
        mainFrame.pack();
        mainFrame.setSize(1200, 800);
    }   

    public void run() {
        new Thread(lP).start();
        
    }
}
