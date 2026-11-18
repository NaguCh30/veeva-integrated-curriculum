// While learning I found that java 8 provides some pre-defined functional interfaces. I practiced them in the below code.

package predefined_functional_interfaces_practice;

import java.util.function.*;

public class PredefinedFunctionalInterfacesUsage {
    
   public static void main(String[] args) {

    
        
        //--------------------------------------------------------------------------------------------------------------//
        System.out.println("\n----------PREDICATE----------");
       
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isEven = n -> n % 2 == 0;

        Predicate<Integer> result1 = isPositive.and(isEven);
        Predicate<Integer> result2 = isPositive.or(isEven);

        System.out.println("5 is Positive: " + isPositive.test(5));
        System.out.println("-1 is Positive: " + isPositive.test(-1));
        System.out.println("5 is positive and even: " + result1.test(5));
        System.out.println("6 is positive and even: " + result1.test(6));
        System.out.println("5 is positive or even: " + result2.test(5));
        System.out.println("-2 is positive or even: " + result2.test(-2));
        System.out.println("-1 is positive or even: " + result2.test(-1));




        //--------------------------------------------------------------------------------------------------------------//
        System.out.println("\n----------FUNCTION----------");
        
        Function<Integer, Integer> square = n -> n * n;
        // First Integer input type, Second Integer return type
        System.out.println("5 square is: " + square.apply(5));

        Function<String, Integer> getLength = name -> name.length();
        System.out.println("'Nagu' Length of the name: " + getLength.apply("Nagu"));

        Function<Integer, Integer> multiplyByTwo = n -> n * 2;
        Function<Integer, Integer> addTen = n -> n + 10;
        Function<Integer, Integer> result = multiplyByTwo.andThen(addTen);
        System.out.println("5 multiplied by 2 and then added 10. Result: " + result.apply(5));



        //--------------------------------------------------------------------------------------------------------------//
        System.out.println("\n----------CONSUMER----------");
        
        Consumer<String> display = name -> System.out.println("Hello, " + name);
        display.accept("Nagu");

        Consumer<Integer> sq = num -> System.out.println("Square of " + num + " is: " + num * num);
        sq.accept(6);

        System.out.println();
        System.out.println("--- Combined Consumers Examples ---");
        Consumer<String> c1 = name -> System.out.println("Name: " + name);
        Consumer<String> c2 = collegeName -> System.out.println("College: Sri Vasavi Engineering College");
        Consumer<String> combined = c1.andThen(c2);
        combined.accept("Nagu Chavala");
        
        System.out.println();

        Consumer<String> printConsumer = name -> System.out.println(name);
        Consumer<String> calcLength = name -> System.out.println("Length: " + name.length());
        Consumer<String> combine = printConsumer.andThen(calcLength);
        combine.accept("Java");



        //--------------------------------------------------------------------------------------------------------------//
        System.out.println("\n----------SUPPLIER----------");

        Supplier<String> message = () -> "Hello Veeva";
        String veevaMessage = message.get();
        System.out.println(veevaMessage);

        System.out.println();

        Supplier<Integer> randomeNumber = () -> (int) (Math.random() * 100);
        System.out.println("First random number: " + randomeNumber.get());
        System.out.println("Second random number: " + randomeNumber.get());
        System.out.println("Third random number: " + randomeNumber.get());



        //--------------------------------------------------------------------------------------------------------------//
        System.out.println("\n----------UnaryOperator----------");
        UnaryOperator<Integer> transformNumber = num -> {
            num += 10;
            num *= 2;
            return num;
        };
        System.out.println("25 id added with 10 and then multiplied with 2. Result= " + transformNumber.apply(25));

        UnaryOperator<String> upper = name -> name.toUpperCase();
        System.out.println("Nagu Chavala -> " + upper.apply("Nagu Chavala"));



        //--------------------------------------------------------------------------------------------------------------//
        System.out.println("\n----------BinaryOperator----------");
        BinaryOperator<Integer> addition = (a, b) -> a + b;
        System.out.println("2 + 8 = " + addition.apply(2, 8));
        
        System.out.println();

        BinaryOperator<Integer> func = (a, b) -> {
            int large = (a > b) ? a : b;
            large *= 10;
            return large;
        };
        System.out.println("Result = " + func.apply(15, 20));



        //--------------------------------------------------------------------------------------------------------------//
        System.out.println("\n----------BiFunction----------");
        BiFunction<Integer, Integer, String> biFunc = (a, b) -> "Sum(" + a + ", " + b + ") = " + (a + b);
        System.out.println(biFunc.apply(30, 70));

   }

}
