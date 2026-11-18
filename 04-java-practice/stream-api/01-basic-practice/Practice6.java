// Converting Object to another object using map()

import java.util.*;

class Employee {
    private int id;
    private String name;
    private double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String getName() { return name; }
    public double getSalary() { return salary; }
}

class EmployeeDTO {
    private String name;
    private double salary;

    EmployeeDTO(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " : " + salary;
    }
}

class Main {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
            new Employee(1, "Nagu", 50000),
            new Employee(2, "Ravi", 40000),
            new Employee(3, "Anil", 60000)
        );

        List<EmployeeDTO> result = employees.stream()
                    .map(emp -> new EmployeeDTO(emp.getName(), emp.getSalary()))
                    .toList();
        
        result.forEach(System.out::println);
    }
}








