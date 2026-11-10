package operations_with_collections.ArrayList;

import java.util.ArrayList;

public class ForEachPractice1 {
    
    public static void main(String[] args) {
        
        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(21);
        list.add(30);
        list.add(31);
        list.add(40);
        list.add(41);
        list.add(50);
        list.add(60);
        list.add(67);
        list.add(70);
        list.add(80);

        System.out.println("Printing list elements using forEach() method: ");
        list.forEach(num -> System.out.print(num + " "));

        System.out.println("\n\nPrinting only even numbers in the list using forEach() method: ");
        list.forEach(num ->  {
            if (num % 2 == 0) {
                System.out.print(num + " ");
            }
        });
    }
}
