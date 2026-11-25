import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentMap;

class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getID() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }

    @Override
    public String toString() { return "ID: " + id + ", Name: " + name + ", Salary: " + salary; }
}

public class Q2EmployeeRecordManager {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(101, "Nagu", 55000));
        employees.add(new Employee(102, "Rahul", 45000));
        employees.add(new Employee(103, "Priya", 75000));
        employees.add(new Employee(104, "Suresh", 60000));
        employees.add(new Employee(105, "Anjali", 85000));
        employees.add(new Employee(106, "Kiran", 40000));
        employees.add(new Employee(107, "Sneha", 95000));
        employees.add(new Employee(108, "Arjun", 55000));
        employees.add(new Employee(109, "Divya", 70000));
        employees.add(new Employee(110, "Vikram", 30000));

        System.out.println("Sorted by ID ascending: ");
        TreeSet<Employee> sortedById = new TreeSet<>(Comparator.comparingInt(Employee::getID));
        sortedById.addAll(employees);
        sortedById.forEach(System.out::println);

        System.out.println("\nSorted by Name ascending: ");
        TreeSet<Employee> sortedByName = new TreeSet<>(Comparator.comparing(Employee::getName));
        sortedByName.addAll(employees);
        sortedByName.forEach(System.out::println);

        System.out.println("\nSorted by Salary ascending: ");
        TreeSet<Employee> sortedBySalary = new TreeSet<>(Comparator.comparingDouble(Employee::getSalary));
        sortedBySalary.addAll(employees);
        sortedBySalary.forEach(System.out::println);

        System.out.println("\nSorted by Salary descending: ");
        TreeSet<Employee> sortedBySalaryDescending = new TreeSet<>(Comparator.comparingDouble(Employee::getSalary).reversed());
        sortedBySalaryDescending.addAll(employees);
        sortedBySalaryDescending.forEach(System.out::println);
    }
}
