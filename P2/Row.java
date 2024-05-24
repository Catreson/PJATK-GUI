import java.util.ArrayList;
import java.util.Collections;

public class Row 
    extends AObject{
    private int width = (int)((2.0/3) * Game.lim_x);
    private int cellWidth;
    private int height = (int)((1.0/10) * Game.lim_y);
    private ArrayList<Boolean> positionsIndices;
    public Row(int x, int y, int noEnemies) {
        super(x, y);
        positionsIndices = new ArrayList<>(Collections.nCopies(noEnemies, true));
        cellWidth = (int)(2.0/3*width/noEnemies);
    }
        

}
