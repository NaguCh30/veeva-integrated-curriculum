package ByteStream;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class InputStreamDemo {
    
    public static void main(String[] args) throws IOException {
        

        // 1. Reading individual bytes
        System.out.println("\n1. Reading individual bytes");
        byte[] data = {65, 66, 67};
        
        InputStream input1 = new ByteArrayInputStream(data);

        int value;

        while ((value = input1.read()) != -1) {
            System.out.println("Byte: " + value);
            System.out.println("Character: " + (char) value);
        }

        input1.close();

        
        // 2. Reading Multiple Bytes
        System.out.println("\n2. Reading Multiple Bytes");
        byte[] source = {10, 20, 30, 40, 50};
        
        ByteArrayInputStream input2 = new ByteArrayInputStream(source);

        byte[] buffer = new byte[3];
        int count;

        while ((count = input2.read(buffer)) != -1) {
            System.out.println("Bytes Read: " + count);

            for (int i = 0; i < count; i++) {
                System.out.println(buffer[i]);
            }
        }

        input2.close();
    }
}
