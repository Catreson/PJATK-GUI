package ex01;
import java.awt.Color;
import java.util.EventObject;

public class ColorChangedEvent 
extends EventObject{
    private Color color;

    public ColorChangedEvent(Object source, Color color){
        super(source);
        this.color = color;
    }
    public Color getColor(){
        return this.color;
    }
}
