//Given a list of 20 numbers. print numbers divisible by 3.

import java.util.ArrayList;
import java.util.Arrays;

class FilterNumbers {
    public static void main(String[] args) {
        Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                            11, 12, 13, 14, 15, 16, 17, 18, 19, 20
                        };
               
        System.out.println("Numbers: " + Arrays.toString(nums));                        
        System.out.println("\nNumbers divisible by 3: ");
        Arrays.stream(nums).filter(n -> n % 3 == 0).forEach(n -> System.out.print(n + " "));
    }
}
