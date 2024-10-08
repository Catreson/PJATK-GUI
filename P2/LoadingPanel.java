import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class LoadingPanel 
extends JPanel
implements Runnable{
    private JProgressBar progressBar;
    private int step;
    public Thread tred;
    private AreaIntruders parent;
    public LoadingPanel(int step, AreaIntruders parent) {
        this.step = step;
        this.parent = parent;
        setLayout(new BorderLayout());
        setSize(400, 200);
        progressBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
        progressBar.setStringPainted(true);
        progressBar.setValue(0);
        JLabel picLabel = new JLabel(new ImageIcon(GameParameters.getPrefix() + "orzel.gif"));
        add(picLabel, BorderLayout.PAGE_START);
        this.add(progressBar, BorderLayout.PAGE_END);
        tred = new Thread(this);
        tred.start();
    }
    public void run(){
        for(int i = 0; i < 100; i++){
            try {
                progressBar.setValue(i);
                Thread.sleep(step);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        parent.getMainFrame().remove(this);
        parent.startGame();
        
    }

}
