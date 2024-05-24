package W10;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StudentView 
    extends JPanel{

        private JLabel lName;
        private JLabel lSurname;
        private JLabel lNumber;
        private JTextField fName;
        private JTextField fSurname;
        private JTextField fNumber;

        public StudentView(){
        this.setLayout(new GridLayout(3,2));
        lName = new JLabel("Name");
        lSurname = new JLabel("Surname");
        lNumber = new JLabel("Number");
        fName = new JTextField();
        fSurname = new JTextField();
        fNumber = new JTextField();
        this.add(lName);
        this.add(fName);
        this.add(lSurname);
        this.add(fSurname);
        this.add(lNumber);
        this.add(fNumber);
        
        }

        public void setForStudent(Student s){
            if(s != null){
            fName.setText(s.getName());
            fSurname.setText(s.getSurname());
            fNumber.setText("" + s.getNumber());
            }
            setBackground(new Color(
            (int)(Math.random()*255),
            (int)(Math.random()*255),
            (int)(Math.random()*255)
        ));
        }
}
