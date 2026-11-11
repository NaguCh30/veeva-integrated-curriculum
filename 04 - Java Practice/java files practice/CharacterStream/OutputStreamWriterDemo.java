package CharacterStream;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class OutputStreamWriterDemo {

    public static void main(String[] args) {

        try (
            OutputStreamWriter writer =
                new OutputStreamWriter(
                    new FileOutputStream("CharacterStream/textfiles/data.txt"),
                    StandardCharsets.UTF_8
                )
        ) {

            writer.write("Hello Java");

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}