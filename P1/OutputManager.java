import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class OutputManager{
    public static BufferedWriter fileWriter;
    private static boolean isSet = false;
    public static void setFile(String filename){
        try {
            fileWriter = new BufferedWriter(new FileWriter(new File(filename) ,true)); //Wrapped with BufferedWriter, so no need to close FileWriter.
            isSet = true;
        }
        catch(IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    public static void setFile() {
        setFile("log.txt");
    }
    public static void printToFileAndConsole(Object message) {
        System.out.println(message.toString());
        if (isSet)
        {
            try {
                fileWriter.write(String.format("<%s>\n%s\n-------------------\n", LocalDateTime.now(), message.toString()));
                fileWriter.flush();
            }
            catch(IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }   
        }
    }
}
