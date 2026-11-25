import java.util.Scanner;
import java.util.TreeMap;

public class Q3EmployeeRecordManager {
    static Scanner sc = new Scanner(System.in);
    static TreeMap<Integer, Employee> employees = new TreeMap<>();

    public static void main(String[] args) {
        while (true) {
            showMenu();
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    System.out.println("Total employees: " + totalEmployees());
                    break;
                case 3:
                    Employee first = firstEmployee();
                    if (first != null) {
                        System.out.println("First employee Details: " + first);
                    } else {
                        System.out.println("No employees found");
                    }
                    break;
                case 4:
                    Employee last = lastEmployee();
                    if (last != null) {
                        System.out.println("Last Employee Details: " + last);
                    } else {
                        System.out.println("No employees found");
                    }
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Please enter a valid choice!");
            }
        }
    }

    public static void addEmployee() {
        System.out.print("Enter employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Employee exists = employees.get(id);
        if (exists != null) {
            System.out.println("Employee with ID " + id + " exists!\nEmployee insertion failed!");
            return;
        }

        System.out.print("Enter employee name: ");
        String name = sc.nextLine();

        System.out.print("Enter employee salary: ");
        double salary = sc.nextDouble();
        sc.nextLine();

        employees.put(id, new Employee(id, name, salary));
        System.out.println("Employee added successfully!");
    }

    public static int totalEmployees() {
        return employees.size();
    }

    public static Employee firstEmployee() {
        if (!employees.isEmpty()) {
            return employees.firstEntry().getValue();
        } else {
            return null;
        }
    }

    public static Employee lastEmployee() {
        if (!employees.isEmpty()) {
            return employees.lastEntry().getValue();
        } else {
            return null;
        }
    }

    public static void showMenu() {
        System.out.println("\n1. Add employee");
        System.out.println("2. get total employees count");
        System.out.println("3. Get first employee details");
        System.out.println("4. Get last employee details");
        System.out.println("5. Exit\n");
    }
}
