package MethodReferences;

interface MessagePrinter {
    void print(String message);
}

class Printer {
    void display(String message) {
        System.out.println(message);
    }
}

public class InstanceMethodReference {
    public static void main(String[] args) {
        Printer printer = new Printer();
        
        // MessagePrinter mp = message -> System.out.println(message);
        // MessagePrinter mp = message -> printer.display(message);
        MessagePrinter mp = printer::display;

        mp.print("Welcome to Veeva Systems.");
    }
}
