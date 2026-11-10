package CharacterStream;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class BufferedFileCopy {

    public static void main(String[] args) {

        try (
            BufferedReader br =
                new BufferedReader(
                    new FileReader(
                        "CharacterStream/textfiles/source.txt",
                        StandardCharsets.UTF_8
                    )
                );

            BufferedWriter bw =
                new BufferedWriter(
                    new FileWriter(
                        "CharacterStream/textfiles/destination.txt",
                        StandardCharsets.UTF_8
                    )
                )
        ) {

            String line;

            while ((line = br.readLine()) != null) {

                bw.write(line);
                bw.newLine();
            }

            System.out.println("File copied successfully!");

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}