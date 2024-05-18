package ex01;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyPanel 
extends JPanel
implements Runnable, ColorListener{
    private JLabel header;
    private JButton startButton;

    public MyPanel(boolean version){
        this.header = new JLabel("Generator");
        this.startButton = new JButton("Start");

        this.setBackground(new Color(255, 0, 0));
        if(version) {
            this.setPreferredSize(new Dimension(200, 200));
            this.setLayout(new BorderLayout());
            this.add(header, BorderLayout.PAGE_START);
            this.add(startButton, BorderLayout.PAGE_END);
            this.startButton.addActionListener(
                e -> {
                    this.startButton.setEnabled(false);
                    new Thread(this).start();
                }
            );

        } else {
            this.setPreferredSize(new Dimension(75, 75));
        }
    }
    @Override
    public void run(){
        while(true){
            this.setBackground(new Color(
                (int)(Math.random()*255),
                (int)(Math.random()*255),
                (int)(Math.random()*255)
            ));
            try {
                Thread.sleep(1000 + (int)(Math.random()*2000));
            } catch (InterruptedException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }

    private java.util.List<ColorListener> listeners = new ArrayList<>();

    public void addColorListener(ColorListener cL){
        listeners.add(cL);
    }

    @Override
    public void setBackground(Color bg){
        Color c = this.getBackground();
        super.setBackground(bg);
        if(listeners != null)
            for(ColorListener cL : listeners){
                cL.onColorChange(new ColorChangedEvent(this, c));
            }
    }

    @Override
    public void onColorChange(ColorChangedEvent e){
        this.setBackground(e.getColor());
    }
}
