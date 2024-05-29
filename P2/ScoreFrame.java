import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ScoreFrame 
    extends JFrame{
        private List<UserScore> lS;
        public ScoreFrame() {
            super("Scoreboard");
            lS = FileSupport.read("leaderboard.csv");
            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            for(UserScore uS : lS){
                c.fill = GridBagConstraints.VERTICAL;
                c.gridy++;
                add(new JLabel(uS.getUsername() + "\t" + uS.getScore()), c);
            }
            pack();
        }

}
