import java.util.*;

class Main {
    public static void main(String[] args) {
        List<String> words = List.of("banana", "apple", "orange", "programming", "java", "development");

        System.out.println("1. Find the non-repeating characters in each string using streams.");
        words.stream()
            .forEach(word -> {
                System.out.print(word + " -> ");
        
                word.chars()
                    .filter(ch -> word.chars()
                        .filter(c -> c == ch)
                        .count() == 1
                    )
                    .mapToObj(ch -> (char) ch)
                    .forEach(ch -> System.out.print(ch + " "));
        
                System.out.println();
            });


        System.out.println("\n2. Find longest word from given list.");
        Optional<String> longestWord = words.stream().max(Comparator.comparingInt(String::length));
        System.out.println("Longest word: " + longestWord.orElse("Sorry, No Words Found"));

        System.out.println("\n3. Find shortest word from given list.");
        Optional<String> shortestWord = words.stream().min(Comparator.comparingInt(String::length));
        System.out.println("Shortest word: " + shortestWord.orElse("Sorry, No Words Found"));
    }
}