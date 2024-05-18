import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class LoadingFrame 
extends JDialog{
    JProgressBar progressBar;

    public LoadingFrame(JFrame frame, String title) {
        super(frame, title);
        setVisible(rootPaneCheckingEnabled);
        setSize(400, 200);
    }
    
}
