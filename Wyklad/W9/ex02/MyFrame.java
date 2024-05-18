package ex02;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class MyFrame 
extends JFrame{
    public static void main(String[] args){
        SwingUtilities.invokeLater(
            () -> new MyFrame()
        );
    }

    public MyFrame()
    {
        this.setSize(640, 480);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*JPanel largePanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){

            }
        };
        largePanel.setPreferredSize(new Dimension(3000, 3000));
        JScrollPane jSP = new JScrollPane(largePanel);
        this.add(jSP);*/
        this.getContentPane().setLayout(new FlowLayout());
        JList<String> jCB = new JList<>(
            new String[] {
                "Jedne",
                "Dwa",
                "Trzy"
            }
        );
        /*jCB.addItemListener(
            e -> {
                System.out.println(e.toString());

            }
        );*/
        JButton jB = new JButton("c");
        jB.addActionListener(
            e -> System.out.println(jCB.getSelectedValuesList())
        );
        this.add(jCB);
        this.add(jB);
    }

}

