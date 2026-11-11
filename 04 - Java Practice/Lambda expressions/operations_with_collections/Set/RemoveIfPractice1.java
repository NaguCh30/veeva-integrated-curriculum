package operations_with_collections.Set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RemoveIfPractice1 {
    
    public static void main(String[] args) {
        
        Set<Integer> numbers = new HashSet<>(Arrays.asList(10, 15, 20, 25, 30, 35, 40));

        System.out.println("Remove all odd numbers");
        numbers.removeIf(num -> num % 2 != 0);
        System.out.println("Modofied Set: ");
        numbers.forEach(num -> System.out.print(num + " "));
    }
}
