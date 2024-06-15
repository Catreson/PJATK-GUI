package W11.ex03;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;

public class MyColorEditor 
    extends JPanel
    implements TableCellEditor{
        private JLabel lRed;
        private JLabel lGreen;
        private JLabel lBlue;
        private JSlider sRed;
        private JSlider sGreen;
        private JSlider sBlue;
        private List<CellEditorListener> listeners  = new ArrayList<>();

        private JPanel preview;

        public MyColorEditor(){
            lRed = new JLabel("Red: " );
            lGreen = new JLabel("Green: " );
            lBlue = new JLabel("Blue: " );

            sRed = new JSlider(0, 255);
            sGreen = new JSlider(0, 255);
            sBlue = new JSlider(0, 255);

            preview = new JPanel();

            setLayout(new GridLayout(4, 2));
            add(lRed);
            add(sRed);
            add(lGreen);
            add(sGreen);
            add(lBlue);
            add(sBlue);
            add(preview);

            sRed.addChangeListener(MyColorEditor.this::colorPreview);
            sGreen.addChangeListener(MyColorEditor.this::colorPreview);
            sBlue.addChangeListener(MyColorEditor.this::colorPreview);

            this.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e){
                        fireStopCellEditing();
                    }
                }
            );

        }

        private void colorPreview(ChangeEvent e){
            preview.setBackground(new Color(sRed.getValue(), sGreen.getValue(), sBlue.getValue()));
        }

        @Override
        public Object getCellEditorValue() {
            return this.preview.getBackground();
        }

        @Override
        public boolean isCellEditable(EventObject anEvent) {
            return true;
        }

        @Override
        public boolean shouldSelectCell(EventObject anEvent) {
            return false;
        }

        @Override
        public boolean stopCellEditing() {
            return false;
        }

        @Override
        public void cancelCellEditing() {
            
        }

        @Override
        public void addCellEditorListener(CellEditorListener l) {
            listeners.add(l);
        }

        @Override
        public void removeCellEditorListener(CellEditorListener l) {
            listeners.remove(l);
        }

        protected void fireStopCellEditing(){
            List<CellEditorListener> delList = new ArrayList<>(listeners);
            for(CellEditorListener l : delList)
                l.editingStopped(new ChangeEvent(this));
            
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            Color color = (Color)value;
            return this;
        }
}
