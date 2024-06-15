package W11.ex01;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class Main 
    extends JFrame{

        public static void main(String[] args){
            SwingUtilities.invokeLater(Main::new);
        }

        public Main(){

            JTable jT = new JTable();
            JScrollPane jS = new JScrollPane();
            jS.add(jT);
            jT.setModel(new MyDataModel());
            add(jS);
            setSize(640, 480);
            setVisible(true);
            setDefaultCloseOperation(3);
        }
    
}
