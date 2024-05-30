import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
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
        private JDialog addedName;
        private SetupFrame setupFrame;
        private ScoreFrame scoreFrame;
        private JLabel nameLabel;

        public StartPanel(AreaIntruders parent) {
            addedName = new JDialog();
            scoreFrame = new ScoreFrame();
            nameLabel = new JLabel("Put your nick: ");
            prompt();
            setLayout(new GridLayout(2,3));
            setSize(new Dimension(400, 600));
            startGame = new JButton("Start");
            setup = new JButton("Setup");
            setName = new JButton("Set");
            scoreboardB = new JButton("Scoreboard");
            nameInputTextField = new JTextField();
            startGame.addActionListener(
                (e) -> {
                    parent.getMainFrame().remove(this);
                    parent.startGame();}
                    );
            setName.addActionListener(
                (e) -> {
                    addedName.setVisible(true);
                    parent.setName(nameInputTextField.getText());}
                    );
            setup.addActionListener(
                        (e) -> {
                                setupFrame = new SetupFrame();
                                setupFrame.setVisible(true);}
                                );
            scoreboardB.addActionListener(
                (e) -> {
                        scoreFrame.setVisible(true);
                        scoreFrame.reaload();}
                        );
            add(nameLabel);
            add(nameInputTextField);
            add(setName);
            add(scoreboardB);
            add(startGame);
            add(setup);
        }

        private void prompt(){
            addedName.setLayout(new BorderLayout());
            JLabel tmpL = new JLabel("SUCCESS");
            JButton tmpB = new JButton("OK");
            tmpB.addActionListener((e) -> addedName.dispose());
            addedName.add(tmpL, BorderLayout.PAGE_START);
            addedName.add(tmpB, BorderLayout.PAGE_END);
            addedName.pack();
        }
}
