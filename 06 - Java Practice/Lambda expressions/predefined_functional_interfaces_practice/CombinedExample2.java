/*
1. Supplier: Supplies the employee's original salary (50000).
2. Predicate: Checks whether the salary is greater than or equal to 30000.
3. Function: Adds a bonus of 5000 to the salary.
4. Consumer: Displays the final salary.
*/

package predefined_functional_interfaces_practice;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CombinedExample2 {
    public static void main(String[] args) {
        
        Supplier<Double> originalSalary = () -> 50000.0;

        Predicate<Double> check = salary -> salary >= 30000;

        Function<Double, Double> addBonus = salary -> salary + 5000;
        
        Consumer<Double> display = salary -> System.out.println("Final salary: " + salary);

        double salary = originalSalary.get();
        if (check.test(salary)) {
            System.out.println("Employee is eligible.");
            double finalSalary = addBonus.apply(salary);
            display.accept(finalSalary);
        } else {
            System.out.println("Employee is not eligible.");
        }
    }
}