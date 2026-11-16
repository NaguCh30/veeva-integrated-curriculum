import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;
    private List<String> skills;

    public Employee(int id, String name, String department,
                    double salary, List<String> skills) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.skills = skills;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public List<String> getSkills() { return skills; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + department +
               " | " + salary;
    }
}

class Main {
    public static void main(String[] args) {

        List<Employee> employees = List.of(
            new Employee(101, "Rahul", "Engineering", 95000,
                List.of("Java", "Spring", "SQL")),

            new Employee(102, "Priya", "HR", 75000,
                List.of("JavaScript", "Excel", "Communication")),

            new Employee(103, "Kiran", "Engineering", 120000,
                List.of("Java", "Python", "React")),

            new Employee(104, "Anjali", "Finance", 110000,
                List.of("Java", "SQL", "Excel")),

            new Employee(105, "Ravi", "Engineering", 85000,
                List.of("JavaScript", "Spring", "MongoDB")),

            new Employee(106, "Sneha", "HR", 105000,
                List.of("Java", "Communication", "Python")),

            new Employee(107, "Arjun", "Finance", 90000,
                List.of("JavaScript", "SQL", "Python")),

            new Employee(108, "Meena", "Engineering", 135000,
                List.of("Java", "React", "Spring"))
        );

        System.out.println("1. Find the names of employees in the Engineering department\nwho earn more than 80000 salary and sort alphabetically based on name.");
        List<String> result1 = employees.stream()
            .filter(e -> e.getDepartment().equals("Engineering"))
            .filter(e -> e.getSalary() > 80000)
            .map(Employee::getName)
            .sorted()
            .toList();

        System.out.println(result1);


        System.out.println("\n2. Extract duplicated list of all employee skills that starts with letter J.");
        List<String> result2 = employees.stream()
            .flatMap(e -> e.getSkills().stream())
            .filter(skill -> skill.startsWith("J"))
            .collect(
                Collectors.groupingBy(
                    Function.identity(),
                    Collectors.counting()
                )
            )
            .entrySet()
            .stream()
            .filter(entry -> entry.getValue() > 1)
            .map(Map.Entry::getKey)
            .sorted()
            .toList();

        System.out.println(result2);

        System.out.println("\n3. Group all employees by department. But only include employees earning more than one lack.");
        Map<String, List<Employee>> result3 = employees.stream()
            .collect(
                Collectors.groupingBy(
                    Employee::getDepartment,
                    Collectors.filtering(
                        e -> e.getSalary() > 100000,
                        Collectors.toList()
                    )
                )
            );
        
        result3.forEach((department, employeeList) -> {
            System.out.println(department + " -> " + employeeList);
        });

        System.out.println("\n4. Filter stream of employees so that only first employee for department is kept.");
        Map<String, Employee> result4 = employees.stream()
            .collect(
                Collectors.toMap(
                    Employee::getDepartment,
                    Function.identity(),
                    (existing, replacement) -> existing,
                    LinkedHashMap::new
                )
            );
        
        result4.forEach((department, employee) -> {
            System.out.println(department + " -> " + employee.getName());
        });
    }
}