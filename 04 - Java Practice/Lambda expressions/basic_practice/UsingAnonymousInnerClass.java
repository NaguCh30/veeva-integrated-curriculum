package basic_practice;

public class UsingAnonymousInnerClass {
    
    public static void main(String[] args) {

        System.out.println("Providing behavior using Anonymous Inner Class");
        System.out.println("For the sample interface Greeting");

        Greeting greeting = new Greeting() {
            
            @Override 
            public void sayHello() {
                System.out.println("\nHello, Good Morning!");
                System.out.println("Method got its behaviour through Anonymous Inner Class");
            }
        };

        greeting.sayHello();

    }

}
