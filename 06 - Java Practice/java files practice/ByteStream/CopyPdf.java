package ByteStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class CopyPdf {
    
    public static void main(String[] args) {
        
        try (
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("ByteStream/pdfs/STLC.pdf"));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("ByteStream/pdfs/CopyOfSTLC.pdf"))
        ) {
            
            byte[] buffer = new byte[4096];
            int count;

            while((count = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, count);
            }

            System.out.println("PDF copied successfully!");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
