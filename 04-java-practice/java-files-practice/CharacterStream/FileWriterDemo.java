package CharacterStream;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileWriterDemo {

    public static void main(String[] args) {

        String name = "Nagu";
        String course = "B.Tech CSE";
        int year = 3;

        try (
            FileWriter writer =
                new FileWriter(
                    "CharacterStream/textfiles/student-details.txt",
                    StandardCharsets.UTF_8
                )
        ) {

            writer.write("STUDENT DETAILS\n");

            writer.write("Name: " + name + "\n");

            writer.write("Course: " + course + "\n");

            writer.write("Year: " + year + "\n");

            System.out.println(
                "Student details saved successfully."
            );

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
