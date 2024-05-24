public class AObject {
    public int x;
    public int y;
    public double vx;
    public double vy;
    public double ax;
    public double ay;


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

    public void move(double tick){
        x += (tick * vx + (tick * tick) * ax / 2.0);
        y += (tick * vy + (tick * tick) * ay / 2.0);
        if(x < 0 || x > Game.lim_x){
            vx *= -1;
            ax *= -1;
        }
    }

}
