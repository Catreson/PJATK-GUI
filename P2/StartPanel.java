import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StartPanel 
    extends JPanel {
        private JButton startGame;
        private JButton setup;
        private JButton scoreboardB;
        private JButton setName;
        private JTextField nameInputTextField;
        private AreaIntruders parent;
        private JDialog addedName;
        private JLabel nameLabel;

        public StartPanel(AreaIntruders parent) {
            this.parent = parent;
            addedName = new JDialog();
            addedName.setLayout(new BorderLayout());
            nameLabel = new JLabel("Put your nick: ");
            JLabel tmpL = new JLabel("SUCCESS");
            JButton tmpB = new JButton("OK");
            tmpB.addActionListener((e) -> addedName.dispose());
            addedName.add(tmpL, BorderLayout.PAGE_START);
            addedName.add(tmpB, BorderLayout.PAGE_END);
            addedName.pack();
            setLayout(new GridLayout(2,3));
            setSize(new Dimension(400, 600));
            startGame = new JButton("Start");
            setup = new JButton("Setup");
            setName = new JButton("Set");
            scoreboardB = new JButton("Scoreboard");
            startGame.addActionListener(
                (e) -> {
                    parent.getMainFrame().remove(this);
                    parent.startGame();}
                    );
            setName.addActionListener(
                (e) -> {
                    addedName.setVisible(true);}
                    );
            nameInputTextField = new JTextField();
            add(nameLabel);
            add(nameInputTextField);
            add(setName);
            add(scoreboardB);
            add(startGame);
            add(setup);
        }
}
