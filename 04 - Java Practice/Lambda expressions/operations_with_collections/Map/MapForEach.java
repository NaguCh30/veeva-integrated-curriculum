package operations_with_collections.Map;

import java.util.HashMap;
import java.util.Map;

public class MapForEach {
    
    public static void main(String[] args) {
        
        Map<String, Integer> students = new HashMap<>();
        
        students.put("Ravi", 85);
        students.put("Kiran", 90);
        students.put("Arjun", 78);
        students.put("Priya", 95);

        students.forEach((key, value) -> {
            System.out.println(key + " got " + value + " marks.");
        });
    }
}
