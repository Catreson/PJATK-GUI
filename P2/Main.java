public class Main {
    public static void main(String args[]) {
        AreaIntruders areaInvaders = new AreaIntruders();
        Thread game = new Thread(areaInvaders);
        game.start();
    }
}