import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ScoreFrame 
    extends JFrame{
        private List<UserScore> lS;
        private ArrayList<JLabel> lL;
        public ScoreFrame() {
            super("Scoreboard");
            lL = new ArrayList<>();
            lS = FileSupport.read(GameParameters.getPrefix() + "leaderboard.csv");
            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            JLabel tmp;
            for(UserScore uS : lS){
                //c.fill = GridBagConstraints.;
                c.gridy++;
                tmp = new JLabel(uS.getUsername() + "\t" + uS.getScore());
                add(tmp, c);
                lL.add(tmp);
            }
            pack();
        }

        public void reaload(){
            lS = FileSupport.read(GameParameters.getPrefix() + "leaderboard.csv");
            UserScore uS;
            for(JLabel l : lL){
                uS = lS.get(lL.indexOf(l));
                l.setText(uS.getUsername() + "\t" + uS.getScore());
            }
        }
}
