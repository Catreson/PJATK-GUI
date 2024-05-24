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
    LoadingPanel lP;
    public AreaIntruders(){
        mainFrame = new JFrame("Area Invaders");
        mainFrame.setSize(1200, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupPanel = new StartPanel();
        //JLabel picLabel = new JLabel(new ImageIcon("orzel.gif"));
        //mainFrame.add(picLabel);
        //try {
            //JLabel picLabel = new JLabel(new ImageIcon(ImageIO.read(new File("orzel.gif"))));
          //  JLabel picLabel = new JLabel(new ImageIcon(this.getClass().getResource("orzel.gif")));
            //mainFrame.add(picLabel);
        //} catch (IOException e) {
         //   // TODO Auto-generated catch block
         //   e.printStackTrace();
        //}
        mainFrame.setVisible(true);
        lP = new LoadingPanel(10);
        mainFrame.add(lP, BorderLayout.PAGE_START);
        mainFrame.add(setupPanel, BorderLayout.PAGE_END);
        mainFrame.pack();
    }   

    public void run() {
        new Thread(lP).start();
        
    }
}
