package W11.ex01;
import javax.swing.table.AbstractTableModel;

public class MyDataModel 
    extends AbstractTableModel{

    @Override
    public int getRowCount() {
        return 10;
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return (rowIndex + 1) * (columnIndex + 1);
    }

}
