import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentFileHandler {

    private final File file;

    public StudentFileHandler() {
        this.file = new File("student.txt");
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

    public List<Student> readAllStudents() {

        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                Student student = new Student(
                    Integer.parseInt(data[0]),
                    data[1],
                    Integer.parseInt(data[2]),
                    Integer.parseInt(data[3]),
                    Integer.parseInt(data[4]),
                    Integer.parseInt(data[5]),
                    Integer.parseInt(data[6]),
                    Integer.parseInt(data[7]),
                    data[8]
                );

                students.add(student);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return students;
    }

    public void writeAllStudents(List<Student> students) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            for (Student student : students) {
                writer.write(convertToRecord(student));
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public void appendStudent(Student student) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            writer.write(convertToRecord(student));
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Error appending student: " + e.getMessage());
        }
    }

    
    private String convertToRecord(Student student) {

        return student.getRollNumber() + "," +
               student.getName() + "," +
               student.getAge() + "," +
               student.getTelugu() + "," +
               student.getEnglish() + "," +
               student.getMaths() + "," +
               student.getScience() + "," +
               student.getSocial() + "," +
               student.getGrade();
    }
}
