import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService service = new StudentService();

    public static void main(String[] args) {

        int choice;

        do {
            displayMenu();

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addRecord();
                    break;

                case 2:
                    addAllRecords();
                    break;

                case 3:
                    readRecordByRollNumber();
                    break;

                case 4:
                    readRecordsByName();
                    break;

                case 5:
                    deleteRecord();
                    break;

                case 6:
                    updateRecord();
                    break;

                case 7:
                    System.out.println("Exiting Student Management System...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

            System.out.println();

        } while (choice != 7);

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println("STUDENT MANAGEMENT SYSTEM");
        System.out.println("1. Add Record");
        System.out.println("2. Add All Records");
        System.out.println("3. Read Record on Roll Number");
        System.out.println("4. Read Records on Name");
        System.out.println("5. Delete Record on Roll Number");
        System.out.println("6. Update Record on Roll Number");
        System.out.println("7. Exit");
        System.out.println();
    }

    private static Student getStudentDetails() {

        System.out.print("Enter Roll Number: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Age: ");
        int age = scanner.nextInt();

        System.out.print("Enter Telugu Marks: ");
        int telugu = scanner.nextInt();

        System.out.print("Enter English Marks: ");
        int english = scanner.nextInt();

        System.out.print("Enter Maths Marks: ");
        int maths = scanner.nextInt();

        System.out.print("Enter Science Marks: ");
        int science = scanner.nextInt();

        System.out.print("Enter Social Marks: ");
        int social = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Grade: ");
        String grade = scanner.nextLine();

        return new Student(
                rollNumber, name, age,
                telugu, english, maths, science, social, grade
        );
    }

    private static void addRecord() {

        System.out.println("\n--- Add Student Record ---");

        Student student = getStudentDetails();

        service.addStudent(student);
    }

    private static void addAllRecords() {

        System.out.println("\n--- Add Multiple Student Records ---");

        System.out.print("How many students do you want to add? ");
        int count = scanner.nextInt();
        scanner.nextLine();

        List<Student> students = new ArrayList<>();

        for (int i = 1; i <= count; i++) {

            System.out.println("\nEnter details of Student " + i);

            Student student = getStudentDetails();
            students.add(student);
        }

        service.addAllStudents(students);
    }

    private static void readRecordByRollNumber() {

        System.out.println("\n--- Search Student by Roll Number ---");

        System.out.print("Enter Roll Number: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();

        Student student = service.findByRollNumber(rollNumber);

        if (student != null) {
            System.out.println("\nStudent Found:");
            System.out.println(student);
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void readRecordsByName() {

        System.out.println("\n--- Search Students by Name ---");

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        List<Student> students = service.findByName(name);

        if (students.isEmpty()) {
            System.out.println("No students found with this name.");
        } else {
            System.out.println("\nMatching Student Records:");

            for (Student student : students) {
                System.out.println("----------------------------");
                System.out.println(student);
            }
        }
    }

    private static void deleteRecord() {

        System.out.println("\n--- Delete Student Record ---");

        System.out.print("Enter Roll Number: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();

        service.deleteStudent(rollNumber);
    }

    private static void updateRecord() {

        System.out.println("\n--- Update Student Record ---");

        System.out.print("Enter Roll Number: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("1. Update Age");
        System.out.println("2. Update Name");
        System.out.println("3. Update Both Age and Name");

        System.out.print("Enter update choice: ");
        int updateChoice = scanner.nextInt();
        scanner.nextLine();

        String newName = null;
        Integer newAge = null;

        switch (updateChoice) {

            case 1:
                System.out.print("Enter new age: ");
                newAge = scanner.nextInt();
                scanner.nextLine();
                break;

            case 2:
                System.out.print("Enter new name: ");
                newName = scanner.nextLine();
                break;

            case 3:
                System.out.print("Enter new age: ");
                newAge = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter new name: ");
                newName = scanner.nextLine();
                break;

            default:
                System.out.println("Invalid update choice!");
                return;
        }

        service.updateStudent(
                rollNumber,
                updateChoice,
                newName,
                newAge
        );
    }
}
