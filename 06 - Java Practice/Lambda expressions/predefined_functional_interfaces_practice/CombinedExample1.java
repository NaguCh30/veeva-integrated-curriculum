/*
Suppose we have a list of products, and we want to:
1. Generate a product.
2. Check whether its price is greater than ₹1,000.
3. Apply a 10% discount.
4. Display the final price.
*/

package predefined_functional_interfaces_practice;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CombinedExample1 {
    
    public static void main(String[] args) {
        // 1. Generate product price
        Supplier<Double> priceSupplier = () -> 2000.0;

        // 2. Check price
        Predicate<Double> isEligible = price -> price > 1000;

        // 3. Apply discount
        Function<Double, Double> discount = price -> price - (price * 0.10);
        
        // 4. Display Price
        Consumer<Double> display = price -> System.out.println("Final Price: " + price);

        double price = priceSupplier.get();

        if (isEligible.test(price)) {
            double finalPrice = discount.apply(price);
            display.accept(finalPrice);
        }

    }

}
