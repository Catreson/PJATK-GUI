import java.awt.GridLayout;
import java.awt.Image;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SetupFrame 
    extends JFrame{
        private List<String> lP;
        private HashMap<String, JTextField> hM;
        private JComboBox<ImageIcon> jCB;
        public SetupFrame() {
            super("Setup Frame");
            this.hM = new HashMap<>();
            JTextField tmpF;
            this.lP = GameParameters.getPars();
            System.out.println(lP);
            setLayout(new GridLayout(lP.size() + 2, 2));
            add(new JLabel("Player Image"));
            jCB = new JComboBox<>(new ImageIcon[]{
                new ImageIcon((new ImageIcon(GameParameters.getPrefix() + "bomba.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT))),
                new ImageIcon((new ImageIcon(GameParameters.getPrefix() + "bomba1.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)))
                });
            add(jCB);
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
            for(String p : lP){
                GameParameters.setPar(p, Double.parseDouble(hM.get(p).getText()));
            }
            GameParameters.setPlayerImage((ImageIcon)jCB.getSelectedItem());
        }

}
