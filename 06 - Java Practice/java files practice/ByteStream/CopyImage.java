package ByteStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyImage {
    
    public static void main(String[] args) {
        try (
            FileInputStream input = new FileInputStream("ByteStream/images/VeevaLogo.png");
            FileOutputStream output = new FileOutputStream("ByteStream/images/CopyOfVeevaLogo.png")
        ) {

            byte[] buffer = new byte[4];
            int count;

            while ((count = input.read(buffer)) != -1) {
                output.write(buffer, 0, count);
            }

            System.out.println("Image copied successfully!");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
