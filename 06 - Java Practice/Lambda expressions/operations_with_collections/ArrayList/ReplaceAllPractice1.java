package operations_with_collections.ArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class ReplaceAllPractice1 {
    
    public static void main(String[] args) {
        
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50, 60));

        System.out.println("Original List: ");
        printList(numbers);

        System.out.println("\n1. Multiply every number by 2.");
        numbers.replaceAll(num -> num * 2);
        System.out.println("Modified List: ");
        printList(numbers);

        System.out.println("\n2. Add 5 to every number.");
        numbers.replaceAll(num -> num + 5);
        System.out.println("Modified List: ");
        printList(numbers);

        System.out.println("\n3. Replace every even number with 0.");
        numbers.replaceAll(num -> num % 2 == 0 ? 0 : num);
        System.out.println("Modified List: ");
        printList(numbers);

        System.out.println("\n4. Replace numbers greater than 30 with 100.");
        numbers.replaceAll(num -> num > 30 ? 100 : num);
        System.out.println("Modified List: ");
        printList(numbers);

        System.out.println("\n5. Square every number.");
        numbers.replaceAll(num -> num * num);
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
