import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput 
implements KeyListener{
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e);
        System.out.println("1");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e);
        System.out.println("2");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e);
        System.out.println("3");
    }
}
