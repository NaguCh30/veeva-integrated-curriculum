package operations_with_collections.ArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class ForEachPractice2 {
    
    public static void main(String[] args) {
        
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Java", "Python", "JavaScript", "React", "Spring Boot"));

        System.out.println("Print all languages using a lambda expression.");
        list.forEach(name -> System.out.println(name + " "));
        
        System.out.println("\nPrint all languages using a method reference.");
        list.forEach(System.out::println);

        System.out.println("\nPrint each language with the prefix \"Learning: \".");
        list.forEach(name -> {
            System.out.println("Learning: " + name);
        });

        System.out.println("\nPrint the length of every language.");
        list.forEach(name -> {
            System.out.println(name + ": " + name.length());
        });


        System.out.println("\nUse a separate Consumer<String> variable and pass it to forEach().");
        Consumer<String> consumer = name -> System.out.println(name);
        list.forEach(consumer);
    }
}
