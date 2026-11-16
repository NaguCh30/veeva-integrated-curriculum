import java.util.*;
import java.util.stream.*;

class Employee {
    private int id;
    private String name;
    private String job;
    private double salary;

    public Employee(int id, String name, String job, double salary) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }
}

class Company {
    private int id;
    private String name;
    private String location;
    private String type;
    private List<Employee> employees;

    public Company(int id, String name, String location,
                   String type, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.type = type;
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}

class Main {
    public static void main(String[] args) {
        List<Company> companies = List.of(

                new Company(1, "Google", "USA", "Software",
                    List.of(
                        new Employee(101, "Rahul", "Developer", 80000),
                        new Employee(102, "Priya", "Tester", 60000)
                    )
                ),
            
                new Company(2, "Amazon", "USA", "Software",
                    List.of(
                        new Employee(103, "Kiran", "Manager", 90000),
                        new Employee(104, "Anjali", "Developer", 75000)
                    )
                ),
            
                new Company(3, "HDFC", "India", "Finance",
                    List.of(
                        new Employee(105, "Ravi", "Analyst", 50000),
                        new Employee(106, "Sneha", "Accountant", 55000)
                    )
                )
            );
        
        List<String> employeeNames = companies.stream()
            .flatMap(company -> company.getEmployees().stream())
            .map(Employee::getName)
            .toList();

        System.out.println(employeeNames);
    }
}