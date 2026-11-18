package basic_practice;

public class UsingImplementingClass implements Greeting {

    @Override 
    public void sayHello() {
        System.out.println("\nHello, Goodmorning");
        System.out.println("Method got its behaviour through Implementing Class");
    }

}

class Main {

    public static void main(String[] args) {
        
        System.out.println("Providing behavior using Implementation Inner Class");
        System.out.println("For the sample interface Greeting");

        UsingImplementingClass impClass = new UsingImplementingClass();
        impClass.sayHello();

    }

}