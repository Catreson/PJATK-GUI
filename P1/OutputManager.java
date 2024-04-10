import java.util.logging.FileHandler;

public class OutputManager {
    private OutputManager outputManager;
    private FileHandler fh;
    public OutputManager() {
        if(outputManager != null)
            return;

        outputManager = this;
    }
    public static void printToFileAndConsole(String message) {
        System.out.println(message); // Print to console
        
    }
    public static void printlnToFileAndConsole() {}
    public static void printfToFileAndConsole() {}
}
