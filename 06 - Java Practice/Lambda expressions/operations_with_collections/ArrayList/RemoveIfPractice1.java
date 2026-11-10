package operations_with_collections.ArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class RemoveIfPractice1 {
    
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(10, 12, 15, 20, 22, 25, 29, 30, 33, 35, 40, 45, 50));

        System.out.println("Original List: ");
        printList(numbers);

        System.out.println("\n1. Remove all odd numbers.");
        numbers.removeIf(num -> num % 2 != 0);
        System.out.println("Modified List: ");
        printList(numbers);

        System.out.println("\n2. Remove all numbers greater than 30.");
        numbers.removeIf(num -> num > 30);
        System.out.println("Modified List: ");
        printList(numbers);

        System.out.println("\n3. Remove all numbers divisible by 5.");
        numbers.removeIf(num -> num % 5 == 0);
        System.out.println("Modified List: ");
        printList(numbers);

        System.out.println("\n4. Remove all numbers between 20 and 40 (exclusive).");
        numbers.removeIf(num -> num > 20 && num < 40);
        System.out.println("Modified List: ");
        printList(numbers);

        System.out.println("\n5. Remove all numbers less than 25 and print the boolean return value.");
        boolean result = numbers.removeIf(num -> num < 25);
        System.out.println("Result: " + result);
        System.out.println("Modified List: ");
        printList(numbers);

    }

    private static void printList(ArrayList<Integer> list) {
        for (int num : list) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}
