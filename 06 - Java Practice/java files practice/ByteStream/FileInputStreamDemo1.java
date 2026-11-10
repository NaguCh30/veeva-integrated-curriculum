package ByteStream;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamDemo1 {
    
    public static void main(String[] args)  {
        
        byte[] buffer = new byte[1024];

        try (FileInputStream input = new FileInputStream("student.txt");) {

            int count;
            while ((count = input.read(buffer)) != -1) {
                System.out.write(buffer);
            }

            input.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } 
    }
}
