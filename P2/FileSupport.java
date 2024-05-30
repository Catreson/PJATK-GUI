import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileSupport {
    public static List<UserScore> read(String filename){
        List<UserScore> userScores = new ArrayList<>();
        String record;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while ((record = reader.readLine()) != null) {
                String[] val = record.split(",");
                userScores.add(new UserScore(val[0], Integer.parseInt(val[1])));
            }
        }
        catch(IOException e){
            System.out.println(e);
            e.printStackTrace();
        }
        return userScores;
    }

    public static void write(List<UserScore> lS, String filename){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for(UserScore uS : lS){
                writer.write(uS.getUsername() + "," + uS.getScore() + '\n');
            }
        }
        catch(IOException e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
