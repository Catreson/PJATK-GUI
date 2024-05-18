package ex01;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
        JPanel cPanel = new JPanel();
        MyPanel generator = new MyPanel(true);
        generator.addColorListener(
            new ColorListener() {
                int cnt = 0;
                @Override
                public void onColorChange(ColorChangedEvent e) {
                    System.out.println("Color changed("+(cnt++)+")");
                    System.out.println(e.getColor());
                }
            }
        );
        cPanel.add(generator);
        JButton jButton= new JButton("^");
        jButton.addActionListener(
            e -> generator.setBackground(new Color(255, 0, 0))
        );
        cPanel.add(jButton);
        this.add(cPanel);
        JPanel dPanel = new JPanel();
        MyPanel mp1 = new MyPanel(false);
        MyPanel mp2 = new MyPanel(false);
        MyPanel mp3 = new MyPanel(false);
        MyPanel mp4 = new MyPanel(false);
        generator.addColorListener(mp1);
        mp1.addColorListener(mp2);
        mp2.addColorListener(mp3);
        mp3.addColorListener(mp4);
        dPanel.add(mp1);
        dPanel.add(mp2);
        dPanel.add(mp3);
        dPanel.add(mp4);
        this.add(dPanel, BorderLayout.PAGE_END);
        this.setSize(640, 480);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

