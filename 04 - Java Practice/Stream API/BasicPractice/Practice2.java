/*
Requirements:
1. Get a Stream from the employee List.
2. Select employees whose salary is greater than ₹30,000.
3. Extract their names.
4. Sort their names alphabetically.
5. Display the names.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class Employee {
    private int id;
    private String name;
    private double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}

class Main {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();

        Employee emp1 = new Employee(1, "Raju", 25000);
        Employee emp2 = new Employee(2, "Raghu", 20000);
        Employee emp3 = new Employee(3, "Kumar", 30000);
        Employee emp4 = new Employee(4, "Mani", 70000);
        Employee emp5 = new Employee(5, "Sharma", 80000);
        Employee emp6 = new Employee(6, "Kundhan", 85000);

        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);
        employees.add(emp5);
        employees.add(emp6);

        Stream<Employee> stream = employees.stream();
        List<String> names = stream.filter(employee -> employee.getSalary() > 30000)
                                    .map(Employee::getName)
                                    .sorted()
                                    .toList();

        names.forEach(name -> System.out.println(name));
    }
}
