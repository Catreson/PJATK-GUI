import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AreaIntruders 
implements Runnable{
    private JFrame mainFrame;
    private StartPanel setupPanel;
    public AreaIntruders(){
        mainFrame = new JFrame("Area Invaders");
        mainFrame.setSize(1200, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupPanel = new StartPanel();
        mainFrame.add(setupPanel);
        JLabel picLabel = new JLabel(new ImageIcon("orzel.gif"));
        mainFrame.add(picLabel);
        //try {
            //JLabel picLabel = new JLabel(new ImageIcon(ImageIO.read(new File("orzel.gif"))));
          //  JLabel picLabel = new JLabel(new ImageIcon(this.getClass().getResource("orzel.gif")));
            //mainFrame.add(picLabel);
        //} catch (IOException e) {
         //   // TODO Auto-generated catch block
         //   e.printStackTrace();
        //}
        mainFrame.setVisible(true);
    }   

    public void run() {

    }
}
