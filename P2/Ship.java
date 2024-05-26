import javax.swing.ImageIcon;

public class Ship 
    extends AObject{
    private ImageIcon view;
    
    public Ship(int x, int y) {
        super(x, y);
        view = new ImageIcon("bomba.jpg");
    }

    public int getCenter(){
        return 0;
    }
}
