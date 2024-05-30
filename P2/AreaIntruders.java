
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;



public class AreaIntruders{
    private JFrame mainFrame;

    private StartPanel setupPanel;
    private Board board;
    private Game game;
    private JPanel welcomePanel;
    private JMenuBar mBar;
    private String name;
    private JPanel controlPanel;
    LoadingPanel lP;
    public AreaIntruders(){
        setup();
        startMenu();
    }   


    private void setup(){
        mBar = new JMenuBar();
        mainFrame = new JFrame("Area Invaders");
        mainFrame.setResizable(false);
        name = "Bomba";
        JMenu setings = new JMenu("Game");
        JMenuItem setup = new JMenuItem("Setup");
        JMenuItem rulebook = new JMenuItem("Rulebook");
        setup.addActionListener(
            (e) -> {
                SetupFrame sF = new SetupFrame();
                sF.setVisible(true);}
        );
        rulebook.addActionListener(
            (e) -> {
                JDialog jD = new JDialog();
                jD.add(new JLabel("<html>Galaktyka Droga Mleczna została opanowana przez złych kosmitów.<br>" + 
                                        "Pokonać ich może tylko załoga Gwiezdnego Patrolu, na czele której stoi Kapitan Bomba. <br>" +
                                        "Używaj strzałek aby się przemieszczać i spacji aby strzelić. Pokonaj jak najwięcej kosmitów!<br>" +
                                        "Użyj p aby zapauzować</html>"));
                jD.setVisible(true);
                jD.pack();}
        );
        setings.add(setup);
        setings.add(rulebook);
        mBar.add(setings);
        mainFrame.setJMenuBar(mBar);
        
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setIconImage(new ImageIcon(GameParameters.getPrefix() + "bomba.jpg").getImage());
        mainFrame.setSize(1200, 800);
        setupPanel = new StartPanel(this);
    }

    public void loadGame(){
        mainFrame.remove(welcomePanel);
        lP = new LoadingPanel(10, this);
        mainFrame.add(lP);
        mainFrame.pack();
    }

    public void startGame(){
        mainFrame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        this.game = new Game(this);
        controlPanel = prepareControlPanel();
        board = game.getBoard();
        c.gridx = 0;
        c.gridy=1;
        mainFrame.add(controlPanel, c);
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        mainFrame.add(board, c);
        mainFrame.pack();
    }

    public void endGame(){
        mainFrame.remove(board);
        mainFrame.remove(controlPanel);
        startMenu();
        List<UserScore> lS = FileSupport.read(GameParameters.getPrefix() + "leaderboard.csv");
        if(Integer.parseInt(Scoreboard.getScore()) > lS.getLast().getScore()){
            UserScore tmp = new UserScore(name, Integer.parseInt(Scoreboard.getScore()));
            lS.add(tmp);
            lS = lS.stream().sorted( (u1, u2) -> u2.getScore().compareTo(u1.getScore())).limit(10).collect(Collectors.toList());
            FileSupport.write(lS, GameParameters.getPrefix() + "leaderboard.csv");
            @SuppressWarnings("unused")
            CongratsDialog jD = new CongratsDialog(lS.indexOf(tmp));
            
        }
    }

    private JPanel prepareControlPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));
        JButton left = new JButton("<");
        JButton right = new JButton(">");
        JButton space = new JButton("-");
        panel.add(left);
        panel.add(space);
        panel.add(right);
        left.addActionListener(
            (e) -> game.onButtonPressed(-1));
        right.addActionListener(
            (e) -> game.onButtonPressed(1));
        space.addActionListener(
            (e) -> game.onSpacePressed());
        left.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        right.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        space.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        return panel;
    }

    private void startMenu(){
        welcomePanel = new JPanel();
        JPanel img = new JPanel();
        JLabel imag = new JLabel(new ImageIcon(GameParameters.getPrefix() + "bomba.jpg"));
        img.add(imag);
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.add(img);
        welcomePanel.add(setupPanel);
        
        mainFrame.add(welcomePanel);
        mainFrame.pack();
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public void setName(String name){
        this.name = name;
        System.out.println(this.name);
    }
}
