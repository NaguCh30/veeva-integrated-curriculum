// Filter employees whose salary is greater than 40,000 and age is greater than 21.

import java.util.stream.Stream;
import java.util.List;


class Employee {
    private int id;
    private String name;
    private double salary;
    private int age;

    public Employee(int id, String name, double salary, int age) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public int getAge() { return age; }
}

class FilterEmployees {
    public static void main(String[] args) {
        Stream<Employee> stream = Stream.of(
            new Employee(1, "Ramu", 30000, 25),
            new Employee(2, "Raju", 41000, 56),
            new Employee(3, "Kumar", 50000, 45),
            new Employee(4, "Harsha", 35000, 24),
            new Employee(5, "Vardhan", 20000, 20)
        );

        List<String> names = stream.filter(emp -> emp.getSalary() > 40000)
                                    .filter(emp -> emp.getAge() > 21)
                                    .map(Employee::getName)
                                    .toList();

        System.out.println("Employees with 40k salary and age greater than 21: ");
        names.forEach(System.out::println);
    }
}