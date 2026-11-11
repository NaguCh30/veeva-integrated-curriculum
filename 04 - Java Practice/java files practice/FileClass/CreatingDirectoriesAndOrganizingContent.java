package FileClass;

import java.io.File;
import java.io.IOException;

public class CreatingDirectoriesAndOrganizingContent {
    
    public static void main(String[] args) {
        
        // 1. Creating Directory Structure
        File parent = new File("JavaFiles/Documents");

        if (parent.mkdirs()) {
            System.out.println("Directoty structure created");
        } else {
            System.out.println("Falied to create directories");
        }

        File textDirectory = new File(parent, "Notes");
        File imageDirectory = new File(parent, "Images");
        File programDirectory = new File(parent, "Programs");

        textDirectory.mkdirs();
        imageDirectory.mkdirs();
        programDirectory.mkdirs();
        

        //2. Preparing Files to insert into JavaFiles/Documents directory
        File textfile1 = new File(parent, "textfile1.txt");
        File textfile2 = new File(parent, "textfile2.txt");
        File imagefile1 = new File(parent, "iamge1.jpg");
        File imagefile2 = new File(parent, "iamge2.jpg");
        File programfile1 = new File(parent, "Program1.java");
        File programfile2 = new File(parent, "Program2.java");
        File programfile3 = new File(parent, "Program3.java");

        File[] files = new File[]{textfile1, textfile2, imagefile1, imagefile2, programfile1, programfile2, programfile3};

        for (File file : files) {
            try {
                if (file.createNewFile()) {
                    System.out.println(file + " created successfully");
                } else {
                    System.out.println(file + " already exists");
                }
            } catch (IOException e) {
                System.out.println(file + " creation failed: " + e.getMessage());
            }
        }


        // 3. Organizing each file into its respective folders
        File[] allFiles = parent.listFiles();

        for (File file : allFiles) {
        
            if (file.isFile()) {
                String name = file.getName();
                if (name.endsWith(".txt")) {
                    file.renameTo(new File(textDirectory, name));
                } else if (name.endsWith(".jpg")) {
                    file.renameTo(new File(imageDirectory, name));
                } else if (name.endsWith(".java")) {
                    file.renameTo(new File(programDirectory, name));
                }
            }
        }

    }
}
