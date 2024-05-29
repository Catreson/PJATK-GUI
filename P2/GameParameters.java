public class GameParameters {
    private static int resolution = 60;
    private static double initialAlienSpeed = 1;
    private static double AlienIncrement = 0.01;
    private static int noRows = 5;
    private static int noEnemies = 10;
    private static int noLife = 20;
    private static int hardcore = 1;
    private static int hardcoreMultiplier = 15;
    private static int gunCooldown = 100;
    private static int bombCooldownMultiplier = 5;
    private static double gravity = -0.0005;


    public static double getGravity() {
        return gravity;
    }
    public static void setGravity(double gravity) {
        GameParameters.gravity = gravity;
    }
    public static int getHardcoreMultiplier() {
        return hardcoreMultiplier;
    }
    public static void setHardcoreMultiplier(int hardcoreMultiplier) {
        GameParameters.hardcoreMultiplier = hardcoreMultiplier;
    }
    public static int getHardcore() {
        return hardcore;
    }
    public static void setHardcore(int hardcore) {
        GameParameters.hardcore = hardcore;
    }
    public static int getResolution() {
        return resolution;
    }
    public static void setResolution(int resolution) {
        GameParameters.resolution = resolution;
    }
    public static double getInitialAlienSpeed() {
        return initialAlienSpeed;
    }
    public static void setInitialAlienSpeed(double initialAlienSpeed) {
        GameParameters.initialAlienSpeed = initialAlienSpeed;
    }
    public static double getAlienIncrement() {
        return AlienIncrement;
    }
    public static void setAlienIncrement(double alienIncrement) {
        AlienIncrement = alienIncrement;
    }
    public static int getNoRows() {
        return noRows;
    }
    public static void setNoRows(int noRows) {
        GameParameters.noRows = noRows;
    }
    public static int getNoEnemies() {
        return noEnemies;
    }
    public static void setNoEnemies(int noEnemies) {
        GameParameters.noEnemies = noEnemies;
    }
    public static int getNoLife() {
        return noLife;
    }
    public static void setNoLife(int noLife) {
        GameParameters.noLife = noLife;
    }
    public static int getGunCooldown() {
        return gunCooldown;
    }
    public static void setGunCooldown(int gunCooldown) {
        GameParameters.gunCooldown = gunCooldown;
    }
    public static int getBombCooldownMultiplier() {
        return bombCooldownMultiplier;
    }
    public static void setBombCooldownMultiplier(int bombCooldownMultiplier) {
        GameParameters.bombCooldownMultiplier = bombCooldownMultiplier;
    }

}
