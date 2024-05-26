import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board 
    extends JPanel{
        private JLabel shipView;
        private Ship ship;
        public Board(){
            setBackground(Color.gray);
            setSize(new Dimension(600, 1000));
        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);

            g.drawImage(new ImageIcon("bomba.jpg").getImage(), 0, 200, this);
        }
}
