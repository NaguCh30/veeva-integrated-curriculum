package operations_with_collections.Map;

import java.util.HashMap;
import java.util.Map;

public class MapExample1 {
    
    public static void main(String[] args) {
        
        Map<String, Integer> students = new HashMap<>();
        
        students.put("Ravi", 85);
        students.put("Kiran", 90);
        students.put("Arjun", 78);
        students.put("Priya", 95);

        System.out.println("\nNames");
        students.keySet().forEach(name -> System.out.println(name));

        System.out.println("\nMarks");
        students.values().forEach(marks -> System.out.println(marks));

        System.out.println("Names and Marks");
        students.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        });

        int[] total = {0};
        students.values().forEach(val -> total[0] += val);
        System.out.println("\nTotal Marks: " + total[0]);
    }
}
