package W10;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class MyRenderer 
    implements ListCellRenderer<Student>{

        private StudentView sV;
        public MyRenderer(){
            sV = new StudentView();
        }

    @Override
    public Component getListCellRendererComponent(JList<? extends Student> list, Student value, int index,
            boolean isSelected, boolean cellHasFocus) {
            sV.setForStudent(value);
            return sV;
        
    }
        
}
