package FileClass;

import java.io.File;
import java.io.IOException;

public class FileClassDemo {
    
    public static void main(String[] args) {
        
        // 1. Creating a directory
        File directory = new File("FilePractice");
        
        if (directory.mkdirs()) {
            System.out.println("Directory Created Successfully");
        } else {
            System.out.println("Directory already exists or Creation failed");
        }

        // 2. Create a file
        File file = new File(directory, "student.txt");
        try {
            if (file.createNewFile()) {
                System.out.println("File created successfully");
            } else {
                System.out.println("File already exists");
            }

            // 3. Check file properties
            System.out.println("\n---------- File Information ----------");
            System.out.println("File name: " + file.getName());
            System.out.println("File Path: " + file.getPath());
            System.out.println("File Absolute Path: " + file.getAbsolutePath());
            System.out.println("File Parent: " + file.getParent());

            System.out.println("\nFile Size: " + file.length());
            System.out.println("Last Modified: " + file.lastModified());

            System.out.println("\nExists: " + file.exists());
            System.out.println("Is File: " +file.isFile());
            System.out.println("Is Directory: " + file.isDirectory());
            System.out.println("Is Hidden: " + file.isHidden());

            
            // 4. List Directory contents
            System.out.println("\n----------- Directory Contents ----------");
            File[] entries = directory.listFiles();

            if (entries != null) {
                for (File entry : entries) {
                    System.out.println(entry.getName());
                }
            }

            // 5. Delete the file
            System.out.println("\n---------- Deletion ----------");
            if (file.delete()) {
                System.out.println("File deleted");
            } else {
                System.out.println("Unable to delete the file");
            }

        } catch (IOException e) {
            System.out.println("File creation failed: " + e.getMessage());
        }
    }
}
