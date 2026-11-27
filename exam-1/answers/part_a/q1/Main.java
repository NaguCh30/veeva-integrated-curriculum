package part_a.q1;

import part_a.q1.model.Employee;
import part_a.q1.service.PayrollManager;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static PayrollManager manager = new PayrollManager();

    public static void main(String[] args) {
        while(true) {
            showMenu();
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addFullTimeEmployee();
                    break;
                case 2:
                    addPartTimeEmployee();
                    break;
                case 3:
                    getDeptWiseTotalSalary();
                    break;
                case 4:
                    getTopKemployees();
                    break;
                case 5:
                    System.out.println("Exiting from the program.");
                    System.exit(0);
                default:
                    System.out.println("Please enter a valid option.");

            }
        }
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("1. Add Full Time employee.");
        System.out.println("2. Add Contract Time employee.");
        System.out.println("3. Get Department Wise Total Salary.");
        System.out.println("4. Get top k employees.");
        System.out.println("5. Exit");
        System.out.println();
    }

    private static void addFullTimeEmployee() {
        System.out.println("Enter full-time employee details");

        System.out.print("Enter employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter employee name: ");
        String name = sc.nextLine();

        System.out.print("Enter employee department: ");
        String dept = sc.nextLine();

        System.out.print("Enter employee base salary: ");
        double salary = sc.nextDouble();

        System.out.print("Enter employee bonus: ");
        double bonus = sc.nextDouble();

        try {
            manager.addFullTimeEmployee(id, name, dept, salary, bonus);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addPartTimeEmployee() {
        System.out.println("Enter contract employee details");

        System.out.print("Enter employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter employee name: ");
        String name = sc.nextLine();

        System.out.print("Enter employee department: ");
        String dept = sc.nextLine();

        System.out.print("Enter hourly rate: ");
        double hr = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter total hours worked: ");
        int worked = sc.nextInt();
        sc.nextLine();

        try {
            manager.addContractEmployee(id, name, dept, hr, worked);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getDeptWiseTotalSalary() {
        Map<String, Double> map = manager.getDepartmentWiseTotalSalary();

        System.out.println("Department ---------- Total Salary");
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " ---------- " + entry.getValue());
        }
    }

    private static void getTopKemployees() {
        System.out.print("Enter threshold value: ");
        int n = sc.nextInt();
        sc.nextLine();

        List<String> employees = manager.getTopEarners(n);
        System.out.println("Top Earners: ");
        for (String name : employees) {
            System.out.println(name);
        }
    }

}
