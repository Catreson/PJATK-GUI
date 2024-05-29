public class Alien {
    private int start;
    public int getStart() {
        return start;
    }
    private int end;
    public int getEnd() {
        return end;
    }
    private boolean alive;
    public boolean isAlive() {
        return alive;
    }

    public void kill()
    {
        alive = false;
        Scoreboard.incrementScore();
        Row.incrementAlienSpeed();
    }
    public Alien(int start, int end){
        this.start = start;
        this.end = end;
        this.alive = true;
    }

}
