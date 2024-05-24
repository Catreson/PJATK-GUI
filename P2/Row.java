import java.util.Arrays;

public class Row 
    extends AObject{
    
    private boolean[] positionsIndices;
    public Row(int x, int y, int noEnemies) {
        super(x, y);
        positionsIndices = new boolean[noEnemies];
        Arrays.fill(positionsIndices, true);
    }
        

}
