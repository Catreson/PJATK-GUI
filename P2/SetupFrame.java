import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SetupFrame 
    extends JFrame{
        private List<String> lP;
        private HashMap<String, JTextField> hM;
        public SetupFrame() {
            super("Setup Frame");
            this.hM = new HashMap<>();
            JTextField tmpF;
            this.lP = GameParameters.getPars();
            System.out.println(lP);
            setLayout(new GridLayout(lP.size() + 1, 2));
            for (String p : lP) {
                add(new JLabel(p));
                tmpF = new JTextField("" + GameParameters.getPar(p));
                hM.put(p, tmpF);
                add(tmpF);
            }
            JButton accept = new JButton("Accept");
            JButton cancel = new JButton("Cancel");
            accept.addActionListener(
                e -> {
                    save();
                    dispose();
                }
            );
            cancel.addActionListener(
                e -> {
                    dispose();
                }
            );
            add(accept);
            add(cancel);
            pack();
        }

        private void save(){
            Double tmp;
            for(String p : lP){
                GameParameters.setPar(p, Double.parseDouble(hM.get(p).getText()));
            }
        }

}
