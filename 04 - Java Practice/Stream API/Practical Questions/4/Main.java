import java.util.*;
import java.time.LocalDate;
import java.util.stream.*;

class Employee {
    private int employeeId;
    private String name;
    private String job;
    private String qualification;
    private LocalDate dateOfJoining;
    private double salary;
    private String department;

    public Employee(int employeeId, String name, String job, String qualification, LocalDate dateOfJoining, double salary, String department) {
        this.employeeId = employeeId;
        this.name = name;
        this.job = job; 
        this.qualification = qualification;
        this.dateOfJoining = dateOfJoining;
        this.salary = salary;
        this.department = department;
    }

    public int getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getJob() { return job; }
    public String getQualification() { return qualification; }
    public LocalDate getDateOfJoining() { return dateOfJoining; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        return employeeId + " | " + name + " | " +
               department + " | " + salary;
    }
}

class Main {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
            new Employee(101, "Rahul", "Developer", "B.Tech",
                LocalDate.of(2020, 5, 10), 75000, "IT"),

            new Employee(102, "Priya", "Tester", "M.Tech",
                LocalDate.of(2021, 3, 15), 60000, "IT"),

            new Employee(103, "Kiran", "Manager", "MBA",
                LocalDate.of(2019, 7, 20), 95000, "HR"),

            new Employee(104, "Anjali", "Developer", "B.Tech",
                LocalDate.of(2022, 1, 10), 85000, "IT"),

            new Employee(105, "Ravi", "HR Executive", "MBA",
                LocalDate.of(2020, 8, 5), 55000, "HR"),

            new Employee(106, "Sneha", "Accountant", "M.Com",
                LocalDate.of(2021, 6, 12), 70000, "Finance"),

            new Employee(107, "Arjun", "Analyst", "MBA",
                LocalDate.of(2023, 2, 18), 65000, "Finance"),

            new Employee(108, "Meena", "Developer", "M.Tech",
                LocalDate.of(2018, 4, 25), 90000, "IT")
        );

        System.out.println("Find the top earning employees department-wise.");
        Map<String, Optional<Employee>> result = employees.stream()
            .collect(
                Collectors.groupingBy(
                    Employee::getDepartment,
                    Collectors.maxBy(
                        Comparator.comparingDouble(Employee::getSalary)
                    )
                )
            );
        result.forEach((department, employee) -> {
            System.out.println(department + " -> " + employee.orElseThrow());
        });
    }
}