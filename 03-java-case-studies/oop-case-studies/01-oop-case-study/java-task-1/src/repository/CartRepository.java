package repository;

import model.ShoppingCart;

import java.util.HashMap;
import java.util.Map;

public class CartRepository {
    private final Map<Long, ShoppingCart> carts = new HashMap<>();

    public ShoppingCart getShoppingCart(long customerId) {
        if (customerId <= 0) {
            throw new IllegalArgumentException("Customer ID should be greater than zero!");
        }

        if (!carts.containsKey(customerId)) {
            carts.put(customerId, new ShoppingCart());
        }

        return carts.get(customerId);
    }
}
