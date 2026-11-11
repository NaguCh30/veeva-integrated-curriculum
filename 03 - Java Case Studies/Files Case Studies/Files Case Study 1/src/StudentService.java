
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentService {

    private final StudentFileHandler fileHandler;

    public StudentService() {
        this.fileHandler = new StudentFileHandler();
    }

    public boolean addStudent(Student student) {

        List<Student> students = fileHandler.readAllStudents();

        for (Student s : students) {
            if (s.getRollNumber() == student.getRollNumber()) {
                System.out.println("Student with this roll number already exists!");
                return false;
            }
        }

        fileHandler.appendStudent(student);

        System.out.println("Student added successfully!");
        return true;
    }

    public void addAllStudents(List<Student> newStudents) {

        List<Student> existingStudents = fileHandler.readAllStudents();

        for (Student newStudent : newStudents) {

            boolean exists = false;

            for (Student existing : existingStudents) {
                if (existing.getRollNumber() == newStudent.getRollNumber()) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                existingStudents.add(newStudent);
                System.out.println("Added student: " + newStudent.getName());
            } else {
                System.out.println("Duplicate roll number skipped: "
                        + newStudent.getRollNumber());
            }
        }

        fileHandler.writeAllStudents(existingStudents);
    }

    public Student findByRollNumber(int rollNumber) {

        List<Student> students = fileHandler.readAllStudents();

        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }

        return null;
    }

    public List<Student> findByName(String name) {

        List<Student> students = fileHandler.readAllStudents();
        List<Student> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                result.add(student);
            }
        }

        return result;
    }

    public boolean deleteStudent(int rollNumber) {

        List<Student> students = fileHandler.readAllStudents();

        boolean removed = students.removeIf(
                student -> student.getRollNumber() == rollNumber
        );

        if (removed) {
            fileHandler.writeAllStudents(students);
            System.out.println("Student deleted successfully!");
            return true;
        }

        System.out.println("Student not found!");
        return false;
    }

    public boolean updateStudent(int rollNumber, Scanner scanner) {

        List<Student> students = fileHandler.readAllStudents();

        for (Student student : students) {

            if (student.getRollNumber() == rollNumber) {

                System.out.println("1. Update Age");
                System.out.println("2. Update Name");
                System.out.println("3. Update Both Age and Name");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    case 1:
                        System.out.print("Enter new age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();

                        student.setAge(age);
                        break;

                    case 2:
                        System.out.print("Enter new name: ");
                        String name = scanner.nextLine();

                        student.setName(name);
                        break;

                    case 3:
                        System.out.print("Enter new age: ");
                        int newAge = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();

                        student.setAge(newAge);
                        student.setName(newName);
                        break;

                    default:
                        System.out.println("Invalid update choice!");
                        return false;
                }

                fileHandler.writeAllStudents(students);

                System.out.println("Student updated successfully!");
                return true;
            }
        }

        System.out.println("Student not found!");
        return false;
    }
}
