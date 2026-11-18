import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private final StudentFileHandler fileHandler;

    public StudentService() {
        this.fileHandler = new StudentFileHandler();
    }

    public boolean addStudent(Student student) {

        ArrayList<Student> students = fileHandler.readStudents();

        for (Student s : students) {
            if (s.getRollNumber() == student.getRollNumber()) {
                System.out.println("Student with this roll number already exists!");
                return false;
            }
        }

        students.add(student);
        fileHandler.writeStudents(students);

        System.out.println("Student added successfully!");
        return true;
    }

    public void addAllStudents(List<Student> newStudents) {

        ArrayList<Student> existingStudents = fileHandler.readStudents();

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

        fileHandler.writeStudents(existingStudents);
    }

    public Student findByRollNumber(int rollNumber) {

        ArrayList<Student> students = fileHandler.readStudents();

        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }

        return null;
    }

    public List<Student> findByName(String name) {

        ArrayList<Student> students = fileHandler.readStudents();
        List<Student> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                result.add(student);
            }
        }

        return result;
    }

    public boolean deleteStudent(int rollNumber) {

        ArrayList<Student> students = fileHandler.readStudents();

        boolean removed = students.removeIf(
                student -> student.getRollNumber() == rollNumber
        );

        if (removed) {
            fileHandler.writeStudents(students);
            System.out.println("Student deleted successfully!");
            return true;
        }

        System.out.println("Student not found!");
        return false;
    }

    public boolean updateStudent(int rollNumber, int choice,
                                 String newName, Integer newAge) {

        ArrayList<Student> students = fileHandler.readStudents();

        for (Student student : students) {

            if (student.getRollNumber() == rollNumber) {

                switch (choice) {

                    case 1:
                        student.setAge(newAge);
                        break;

                    case 2:
                        student.setName(newName);
                        break;

                    case 3:
                        student.setAge(newAge);
                        student.setName(newName);
                        break;

                    default:
                        System.out.println("Invalid update choice!");
                        return false;
                }

                fileHandler.writeStudents(students);

                System.out.println("Student updated successfully!");
                return true;
            }
        }

        System.out.println("Student not found!");
        return false;
    }

    public List<Student> getAllStudents() {
        return fileHandler.readStudents();
    }
}
