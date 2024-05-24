package W10;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

public class MyComboBoxModel<T> 
    extends DefaultComboBoxModel<T> {
        
        java.util.List<T> data;

        public MyComboBoxModel(java.util.List<T> lst){
            data = new ArrayList<>(lst);
        }
        public MyComboBoxModel(){
            data = new ArrayList<>();
        }

        @Override
        public int getSize(){
            return data.size();
        }
        @Override
        public T getElementAt(int index){
            return data.get(index);
        }
        public void add(T s){
            data.add(s);
        }
}


