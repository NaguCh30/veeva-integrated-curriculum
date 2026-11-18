package useful_examples;
// In this class I used Operation interface for practcie

import java.util.Scanner;

public class OperationImplementation2 {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a value: ");
        int a = sc.nextInt();
        System.out.print("\nEnter b value: ");
        int b = sc.nextInt();
        System.out.println();

        System.out.println("Addition: " + performOperation(a, b, (x, y) -> x + y));
        System.out.println("Subtraction: " + performOperation(a, b, (x, y) -> x - y));
        System.out.println("Multiplication: " + performOperation(a, b, (x, y) -> x * y));
        System.out.println("Division: " + performOperation(a, b, (x, y) -> x / y));

    }


    public static int performOperation(int a, int b, Operation operation) {
        int result = operation.calculate(a, b);
        return result;
    }
}
