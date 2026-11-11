package operations_with_collections.ArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class RemoveIfPractice2 {
    
    public static void main(String[] args) {
        
        ArrayList<String> names = new ArrayList<>(Arrays.asList("Java", "Python", "JavaScript", "React", "Spring", "Angular"));
        
        System.out.println("Original List: ");
        printList(names);

        System.out.println("\n1. Remove all strings whose length is less than 6.");
        names.removeIf(name -> name.length() < 6);
        System.out.println("Modified List: ");
        printList(names);

        System.out.println("\n2. Remove all strings starting with J.");
        names.removeIf(name -> name.startsWith("J"));
        System.out.println("Modified List: ");
        printList(names);

        System.out.println("\n3. Remove all strings ending with t.");
        names.removeIf(name -> name.endsWith("t"));
        System.out.println("Modified List: ");
        printList(names);
        
    }

    private static void printList(ArrayList<String> list) {
        for (String name : list) {
            System.out.print(name + ", ");
        }
        System.out.println();
    }
}
