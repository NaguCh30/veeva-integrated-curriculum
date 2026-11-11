package useful_examples;
// In this class I used Operation interface for practcie

import java.util.Scanner;

public class OperationImplementation1 {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a value: ");
        int x = sc.nextInt();
        System.out.println("\nEnter b value: ");
        int y = sc.nextInt();

        System.out.println();
        Operation addition = (a, b) -> a + b;
        Operation subtraction = (a, b) -> a - b;
        Operation multiplication = (a, b) -> a * b;
        Operation division = (a, b) -> a / b;

        System.out.println("Addition: " + addition.calculate(x, y));
        System.out.println("Subtraction: " + subtraction.calculate(x, y));
        System.out.println("Multiplication: " + multiplication.calculate(x, y));
        System.out.println("Division: " + division.calculate(x, y));
    }

}
