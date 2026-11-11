package CharacterStream;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileReaderDemo {
    
    public static void main(String[] args) {
        
        try (FileReader reader = new FileReader("CharacterStream/textfiles/student.txt", StandardCharsets.UTF_8)) {

            char[] buffer = new char[1024];
            int charsRead;

            while ((charsRead = reader.read(buffer)) != -1) {
                System.out.println(new String(buffer, 0, charsRead));
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
