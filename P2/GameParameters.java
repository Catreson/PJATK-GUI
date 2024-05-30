import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.ImageIcon;

public class GameParameters {
    private static String prefix = "";
    private static ImageIcon playerImage = new ImageIcon(prefix + "bomba.png");

    private static Map<String, Double> hM = Stream.of(new Object[][] { 
            { "resolution", 60. }, 
            { "initialAlienSpeed", 1. }, 
            { "AlienIncrement", 0.01 }, 
            { "noRows", 5. },
            { "noEnemies", 10. }, 
            { "noLife", 1. },
            { "hardcore", 1. }, 
            { "hardcoreMultiplier", 15. },
            { "gunCooldown", 100. }, 
            { "bombCooldownMultiplier", 5. }, 
            { "gravity", -0.0005 },
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Double) data[1]));

    public static void setPar(String par, double val){
        hM.put(par, val);
    }

    public static double getPar(String par){
        return hM.get(par);
    }

    public static List<String> getPars(){
        return new ArrayList<String>(hM.keySet());
    }

    public static String getPrefix() {
        return prefix;
    }

    public static ImageIcon getPlayerImage() {
        return playerImage;
    }

    public static void setPlayerImage(ImageIcon playerImage) {
        GameParameters.playerImage = playerImage;
    }
}
