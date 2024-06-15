package W11.ex03;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MyColorRenderer 
    extends  JPanel
    implements TableCellRenderer{
    JPanel colorPreview;

    public MyColorRenderer(){
        this.colorPreview = new JPanel();
        this.setPreferredSize(new Dimension(50, 50));
        add(colorPreview);

    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        this.colorPreview.setBackground((Color)value);
        return this;
    }

}
