import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class CongratsDialog 
    extends JDialog{
        public CongratsDialog(int position){
            setSize(300, 200);
            JLabel label = new JLabel("Gratulacje!\n Jesteś " + (position + 1) + " w rankingu.");
            label.setFont(new Font("Serif", Font.PLAIN, 24));
            add(label);
            pack();
            setVisible(true);
        }
}
