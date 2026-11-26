import model.Student;
import repository.StudentRepository;
import service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        StudentRepository repository = new StudentRepository();
        StudentService service = new StudentService(repository);

        while (true) {
            showMenu();
            System.out.print("Enter your choice: ");
            String input = sc.nextLine();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input please enter a valid choice as number.");
                continue;
            }

            switch (choice) {
                case 1:
                    getStudentsByName(service);
                    break;
                case 2:
                    getStudent(service);
                    break;
                case 3:
                    getStudentMarks(service);
                    break;
                case 4:
                    insertStudent(service);
                    break;
                case 5:
                    removeStudent(service);
                    break;
                case 6:
                    checkExistence(service);
                    break;
                case 7:
                    System.out.println("Exiting from the program...");
                    System.exit(0);
                default:
                    System.out.println("Please enter a valid choice.");
            }
        }
    }

    public static void showMenu() {
        System.out.println("\n1. Get students by name.");
        System.out.println("2. Get student details by ID.");
        System.out.println("3. Get student marks by ID.");
        System.out.println("4. Insert student. (If exist marks will be updated.)");
        System.out.println("5. Remove student by ID.");
        System.out.println("6. Check student existence with ID.");
        System.out.println("7. Exit\n");
    }

    public static void getStudentsByName(StudentService service) {
        try {
            System.out.print("\nEnter name to search for matching students: ");
            String name = sc.nextLine();

            List<Student> students = service.getStudentsByName(name);
            if (students.size() == 0) {
                System.out.println("No student found with name '" + name + "'");
                return;
            }
            System.out.println("\nMatching students: ");
            students.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getStudent(StudentService service) {
        System.out.print("\nEnter student ID to get details: ");
        int id = sc.nextInt();

        try {
            Student student = service.getStudentById(id);
            System.out.println(student);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void  getStudentMarks(StudentService service) {
        System.out.print("\nEnter student ID to check marks: ");
        int id = sc.nextInt();

        try {
            Student student = service.getStudentById(id);
            System.out.println("Student ID: " + student.getId());
            System.out.println("Student Marks: " + student.getMarks());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertStudent(StudentService service) {

        try {
            System.out.println("\nEnter student details");
            System.out.print("Student ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Student Name: ");
            String name = sc.nextLine();

            System.out.print("Student Marks: ");
            double marks = sc.nextDouble();
            sc.nextLine();

            Student student = service.addStudent(id, name, marks);
            System.out.println("Student details: " + student);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeStudent(StudentService service) {
        try {
            System.out.print("\nEnter student ID to remove student: ");
            int id = sc.nextInt();
            sc.nextLine();

            service.removeStudent(id);
            System.out.println("Student with ID " + id + " removed successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void checkExistence(StudentService service) {
        try {
            System.out.print("\nEnter student ID to check existence: ");
            int id = sc.nextInt();
            sc.nextLine();

            Student existed = service.getStudentById(id);
            System.out.println("Student with ID " + id + " existed.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}