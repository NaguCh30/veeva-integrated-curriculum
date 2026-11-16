import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

class Main {
    public static void main(String[] args) {

        System.out.println("========== PROBLEM 1 ==========");

        List<String> words = List.of(
            "java", "code", "Java", "book", "tree",
            "data", "java", "apple", "home", "CODE"
        );

        System.out.println("Original List: " + words);

        List<String> stage1 = words.stream()
            .filter(word -> word.length() == 4)
            .toList();

        System.out.println("After filter: " + stage1);

        List<String> stage2 = stage1.stream()
            .map(String::toUpperCase)
            .toList();

        System.out.println("After uppercase: " + stage2);

        List<String> stage3 = stage2.stream()
            .distinct()
            .toList();

        System.out.println("After distinct: " + stage3);

        List<String> stage4 = stage3.stream()
            .sorted()
            .toList();

        System.out.println("After sorted: " + stage4);

        System.out.println("Final Output: " + stage4);


        System.out.println("\n========== PROBLEM 2 ==========");

        int[] numbers = {2, 6, 8, 5, 7, 6, 3, 10, 8, 4, 12};

        System.out.println("Original Array: " +
            Arrays.toString(numbers));

        List<Integer> result2 = Arrays.stream(numbers)
            .filter(n -> n > 5)
            .map(n -> n * n)
            .distinct()
            .sorted()
            .boxed()
            .toList();

        System.out.println("Final Output: " + result2);


        System.out.println("\n========== PROBLEM 3 ==========");

        List<Integer> nums = List.of(
            1, 2, 3, 2, 4, 5, 1, 3, 3, 6, 7, 5
        );

        System.out.println("Original List: " + nums);

        List<Integer> duplicates = nums.stream()
            .collect(
                Collectors.groupingBy(
                    Function.identity(),
                    LinkedHashMap::new,
                    Collectors.counting()
                )
            )
            .entrySet()
            .stream()
            .filter(entry -> entry.getValue() > 1)
            .map(Map.Entry::getKey)
            .toList();

        System.out.println("Duplicate Numbers: " + duplicates);

        System.out.println("\n========== PROBLEM 4 ==========");

        String str = "swiss";

        System.out.println("Original String: " + str);

        Optional<Character> firstNonRepeating = str.chars()
            .mapToObj(ch -> (char) ch)
            .collect(
                Collectors.groupingBy(
                    Function.identity(),
                    LinkedHashMap::new,
                    Collectors.counting()
                )
            )
            .entrySet()
            .stream()
            .filter(entry -> entry.getValue() == 1)
            .map(Map.Entry::getKey)
            .findFirst();

        System.out.println(
            "First Non-Repeating Character: " +
            firstNonRepeating.orElse(null)
        );
    }
}
