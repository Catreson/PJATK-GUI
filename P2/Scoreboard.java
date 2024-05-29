import java.awt.Dimension;

import javax.swing.JLabel;

public class Scoreboard 
    extends JLabel{
        private static int score = 0;
        private static Scoreboard sboard;
        public Scoreboard(){
            setText(""+score);
            setSize(new Dimension(400, 50));
            sboard = this;
        }

        public static void incrementScore(){
            score++;
            sboard.reload();
        }

        public static String getScore(){
            return String.valueOf(score);
        }

        private void reload(){
            setText(""+score);
        }
}
