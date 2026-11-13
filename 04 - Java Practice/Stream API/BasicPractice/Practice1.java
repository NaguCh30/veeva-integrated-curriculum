// Given a list of numbers: Select even numbers, Multiply each selected number by 3, Select numbers greater than 50, Sort them, Store the result in a List.

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class FilterNumbers {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 11, 12, 14, 20, 35, 36, 49, 90, 70, 66));

        List<Integer> result = numbers.stream()
            .filter(n -> n % 2 == 0)
            .map(n -> n * 3)
            .filter(n -> n > 50)
            .sorted()
            .toList();

        System.out.println("Original List: " + numbers);
        
        System.out.println("\nProcessed List: " + result);
            
    }
}