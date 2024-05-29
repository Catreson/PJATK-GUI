public class AObject {
    public int x;
    public int y;
    public double vx;
    public double vy;
    public double ax;
    public double ay;
    public int width = 0;



    public int getWidth() {
        return width;
    }
    public AObject(){
        //default constructor
    }
    public AObject(int x, int y){
        this(x, y, 0, 0, 0, 0);
    }

    public AObject(int x, int y, double vx, double vy){
        this(x, y, vx, vy, 0, 0);
    }

    public AObject(int x, int y, double vx, double vy, double ax, double ay){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.ax = ax;
        this.ay = ay;
    }

    public int getCenter(){
        return x + width / 2;
    }

    public void move(double tick){
        x += (tick * vx + (tick * tick) * ax / 2.0);
        y += (tick * vy + (tick * tick) * ay / 2.0);
        vx += ax * tick;
        vy += ay * tick;
    }

}
