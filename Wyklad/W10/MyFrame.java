package W10;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
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
        MyComboBoxModel<Student> cBM = new MyComboBoxModel<>(); 
        JButton jB = new JButton("Add");
        JComboBox<Student> jCB = new JComboBox<>(cBM);
        jCB.setRenderer(
            new MyRenderer()
        );
        cBM.add(new Student("Paweł", "Raweł", 123));
        Student s = new Student("Ania", "Wanna", 321);
        cBM.add(s);
        StudentView sV = new StudentView();
        sV.setForStudent(s);
        this.add(sV);
        jB.addActionListener(
            e -> {
                cBM.add(new Student("Andrzej", "Test", 213));
            }
        );

        this.add(jCB);
        this.add(jB);
    }

}

