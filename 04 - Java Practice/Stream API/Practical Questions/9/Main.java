import java.util.List;
import java.util.ArrayList;
import java.util.stream.DoubleStream;
import java.util.OptionalDouble;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
}

class Main {
    public static void main(String[] args) {
        Employee emp1 = new Employee(1, "Ravi", "IT", 50000);
        Employee emp2 = new Employee(2, "Priya", "HR", 35000);
        Employee emp3 = new Employee(3, "Kiran", "IT", 75000);
        Employee emp4 = new Employee(4, "Sneha", "Finance", 60000);
        Employee emp5 = new Employee(5, "Arun", "IT", 45000);
        Employee emp6 = new Employee(6, "Divya", "HR", 55000);

        List<Employee> employees = new ArrayList<>(List.of(emp1, emp2, emp3, emp4, emp5, emp6));

        System.out.println("Extract all employee names using map()");
        employees.stream()
            .map(Employee::getName)
            .forEach(System.out::println);

        System.out.println("\nExtract all employee salaries using mapToDouble()");
        DoubleStream salaries = employees
            .stream()
            .mapToDouble(Employee::getSalary);
        
        salaries.forEach(System.out::println);

        System.out.println("\nCalculate the total salary of all employees using mapToDouble() and sum()");
        double sum = employees.stream()
            .mapToDouble(Employee::getSalary)
            .sum();
        System.out.println(sum);

        System.out.println("\nFind employees whose salary is greater than 45,000 and return only their names");
        employees.stream()
            .filter(e -> e.getSalary() > 45000)
            .map(Employee::getName)
            .forEach(System.out::println);

        System.out.println("\nConvert employee names into uppercase using map()");
        employees.stream()
            .map(Employee::getName)
            .map(String::toUpperCase)
            .forEach(System.out::println);

        System.out.println("\nFind the average salary of IT department employees.");
        OptionalDouble avgSalaryOfIT = employees.stream()
            .filter(e -> e.getDepartment()
            .equals("IT"))
            .mapToDouble(e -> e.getSalary())
            .average();
        if(avgSalaryOfIT.isPresent()){
            System.out.println(avgSalaryOfIT.getAsDouble());
        }
    }
}