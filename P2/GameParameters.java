import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameParameters {
    private static Double resolution = 60.;
    private static Double initialAlienSpeed = 1.;
    private static Double AlienIncrement = 0.01;
    private static Double noRows = 5.;
    private static Double noEnemies = 10.;
    private static Double noLife = 20.;
    private static Double hardcore = 1.;
    private static Double hardcoreMultiplier = 15.;
    private static Double gunCooldown = 100.;
    private static Double bombCooldownMultiplier = 5.;
    private static Double gravity = -0.0005;
    private static Map<String, Double> hM = Stream.of(new Object[][] { 
            { "resolution", resolution }, 
            { "initialAlienSpeed", initialAlienSpeed }, 
            { "AlienIncrement", AlienIncrement }, 
            { "noRows", noRows },
            { "noEnemies", noEnemies }, 
            { "noLife", noLife },
            { "hardcore", hardcore }, 
            { "hardcoreMultiplier", hardcoreMultiplier },
            { "gunCooldown", gunCooldown }, 
            { "bombCooldownMultiplier", bombCooldownMultiplier }, 
            { "gravity", gravity },
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Double) data[1]));

    public static double getGravity() {
        return gravity;
    }
    public static void setGravity(double gravity) {
        GameParameters.gravity = gravity;
    }
    public static double getHardcoreMultiplier() {
        return hardcoreMultiplier;
    }
    public static void setHardcoreMultiplier(double hardcoreMultiplier) {
        GameParameters.hardcoreMultiplier = hardcoreMultiplier;
    }
    public static double getHardcore() {
        return hardcore;
    }
    public static void setHardcore(double hardcore) {
        GameParameters.hardcore = hardcore;
    }
    public static double getResolution() {
        return resolution;
    }
    public static void setResolution(double resolution) {
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
    public static double getNoRows() {
        return noRows;
    }
    public static void setNoRows(double noRows) {
        GameParameters.noRows = noRows;
    }
    public static double getNoEnemies() {
        return noEnemies;
    }
    public static void setNoEnemies(double noEnemies) {
        GameParameters.noEnemies = noEnemies;
    }
    public static double getNoLife() {
        return noLife;
    }
    public static void setNoLife(double noLife) {
        GameParameters.noLife = noLife;
    }
    public static double getGunCooldown() {
        return gunCooldown;
    }
    public static void setGunCooldown(double gunCooldown) {
        GameParameters.gunCooldown = gunCooldown;
    }
    public static double getBombCooldownMultiplier() {
        return bombCooldownMultiplier;
    }
    public static void setBombCooldownMultiplier(double bombCooldownMultiplier) {
        GameParameters.bombCooldownMultiplier = bombCooldownMultiplier;
    }

    public static void setPar(String par, double val){
        hM.put(par, val);
    }

    public static double getPar(String par){
        return hM.get(par);
    }

    public static List<String> getPars(){
        return new ArrayList<String>(hM.keySet());
    }
}
