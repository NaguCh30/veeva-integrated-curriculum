package operations_with_collections.Set;

import java.util.HashSet;
import java.util.Set;

public class ForEachPractice1 {
    
    public static void main(String[] args) {
        
        Set<String> languages = new HashSet<>();
        languages.add("Java");
        languages.add("Python");
        languages.add("JavaScript");
        languages.add("React");
        languages.add("Spring");

        languages.forEach(language -> System.out.println(language));
    }
}
