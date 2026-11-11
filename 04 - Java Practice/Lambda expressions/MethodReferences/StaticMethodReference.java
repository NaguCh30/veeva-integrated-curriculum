package MethodReferences;

@FunctionalInterface 
interface NumberOperation {
    int operate(int num);
}

class Calculator {
    static int square(int num) {
        return num * num;
    }
}

public class StaticMethodReference {
    
    public static void main(String[] args) {

        // NumberOperation operation = num -> num * num;

        // NumberOperation operation = num -> Calculator.square(num);

        NumberOperation operation = Calculator::square;

        int val = 5;

        System.out.println("Value got with static method reference: " + operation.operate(val));

    }
}
