package operations_with_collections.ArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class ReplaceAllPractice2 {
    

    public static void main(String[] args) {

        ArrayList<String> languages = new ArrayList<>(Arrays.asList("java", "python", "javascript", "react", "spring"));

        System.out.println("Original List: ");
        printList(languages);

        System.out.println("\n1. Convert all strings to uppercase.");
        languages.replaceAll(name -> name.toUpperCase());
        System.out.println("Modified List: ");
        printList(languages);

        System.out.println("\n2. Convert all strings to lowercase.");
        languages.replaceAll(name -> name.toLowerCase());
        System.out.println("Modified List: ");
        printList(languages);

        System.out.println("\n3. Add \"Learning: \" as a prefix to every string.");
        languages.replaceAll(name -> "Learning " + name);
        System.out.println("Modified List: ");
        printList(languages);

        System.out.println("\n4. Append the length of each string to its name. Example: java → java-4.");
        languages.replaceAll(name -> {
            int len = name.length();
            return name + len;
        });
        System.out.println("Modified List: ");
        printList(languages);
        
    }

    private static void printList(ArrayList<String> list) {
        for (String name : list) {
            System.out.print(name + ", ");
        }
        System.out.println();
    }
}
