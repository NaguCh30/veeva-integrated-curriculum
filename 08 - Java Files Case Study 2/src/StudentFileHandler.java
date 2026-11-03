import java.io.*;
import java.util.ArrayList;

public class StudentFileHandler {

    private final File file;

    public StudentFileHandler() {
        this.file = new File("student.dat");
        createFile();
    }

    private void createFile() {

        try {
            if (file.createNewFile()) {
                System.out.println("Student file created successfully!");
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    public void writeStudents(ArrayList<Student> students) {

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(file))) {

            oos.writeObject(students);

        } catch (IOException e) {
            System.out.println("Error writing student objects: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Student> readStudents() {

        if (file.length() == 0) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(file))) {

            return (ArrayList<Student>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading student objects: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
