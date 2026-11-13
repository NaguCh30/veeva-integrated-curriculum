import java.util.Arrays;
import java.util.List;

class UnderstandExecutionOrder {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 10, 15);

        System.out.println("Numbers: " + numbers);
        System.out.println();
        
        numbers.stream()
                .filter( n -> {
                    System.out.println("A: " + n);
                    return n > 5;
                })
                .map(n -> {
                    System.out.println("B: " + n);
                    return n + 10;
                })
                .forEach(n -> System.out.println("C: " + n));
    }    
}
