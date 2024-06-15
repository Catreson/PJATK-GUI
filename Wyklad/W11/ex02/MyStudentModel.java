package W11.ex02;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class MyStudentModel 
    extends AbstractTableModel{
        private List<Student> lS;

        public MyStudentModel(){
            lS = new ArrayList<>(){{
                add(new Student("Ania", "Wania", 2317));
                add(new Student("Kasia", "Dania", 2137));
                add(new Student("Paweł", "Konkel", 3127));
            }};
        }
    @Override
    public int getRowCount() {
        return lS.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student s = lS.get(rowIndex);
        return switch(columnIndex){
            case 0 -> s.getName();
            case 1 -> s.getSurname();
            case 2-> s.getNumber();
            default -> "empty";
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex){
        return switch(columnIndex){
            case 0 -> String.class;
            case 1 -> String.class;
            case 2-> Integer.class;
            default -> super.getColumnClass(columnIndex);
        };
    }

    @Override 
    public String getColumnName(int columnIndex){
        return switch(columnIndex){
            case 0 -> "Imię";
            case 1 -> "Nazwisko";
            case 2-> "Numer";
            default -> super.getColumnName(columnIndex);
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Student s = lS.get(rowIndex);
        switch (columnIndex) {
            case 0:
                s.setName((String)aValue);    
                break;
            case 1:
                s.setSurname((String)aValue);
                break;
            case 2:
                s.setNumber((int)aValue);
                break;
        
            default:
                break;
        }
    }
}
