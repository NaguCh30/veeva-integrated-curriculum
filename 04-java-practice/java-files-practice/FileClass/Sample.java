package FileClass;
import java.io.File;
import java.io.IOException;

public class Sample {
    public static void main(String[] args) throws IOException {
        
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Working Directory: " + currentDirectory);

        File relativeFile = new File("../student.txt");

        System.out.println("\nRelative Path: " + relativeFile.getPath());
        System.out.println("Absolute Path: " + relativeFile.getAbsolutePath());
    }
}