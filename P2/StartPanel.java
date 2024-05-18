import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StartPanel 
    extends JPanel {
        private JButton startGame;
        private JButton setup;
        private JTextField nameInputTextField;

        public StartPanel() {
            startGame = new JButton("Start");
            setup = new JButton("Setup");
            nameInputTextField = new JTextField("Your name");
            add(nameInputTextField);
            add(startGame);
            add(setup);
        }
}
