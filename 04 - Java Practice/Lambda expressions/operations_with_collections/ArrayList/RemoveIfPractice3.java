package operations_with_collections.ArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

public class RemoveIfPractice3 {
    public static void main(String[] args) {
        
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(10, 12, 15, 20, 22, 25, 29, 30, 33, 35, 40, 45, 50));

        System.out.println("Original List: ");
        printList(numbers);

        Predicate<Integer> predicate = num -> num % 2 == 0;
        numbers.removeIf(predicate);

        System.out.println("\nList after performing removeIf() with predicate: ");
        printList(numbers);

    }

    private static void printList(ArrayList<Integer> list) {
        for (int num : list) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
