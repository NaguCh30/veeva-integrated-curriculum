package basic_practice;

public class UsingLambdaExpression {
    
    public static void main(String[] args) {
        
        System.out.println("Providing behavior using  Lambda Expression");
        System.out.println("For the sample interface Greeting");

        Greeting greeting = () -> {
            System.out.println("\nHello, Good Morning!");
            System.out.println("Method got its implementation from Lambda Expression");
        };

        greeting.sayHello();

    }

}
