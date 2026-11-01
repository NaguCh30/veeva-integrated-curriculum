package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    private final Map<Long, CartItem> items = new HashMap<>();

    public void addItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Provide valid product details!");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero!");
        }

        if(items.containsKey(product.getProductId())) {
            CartItem cartItem = items.get(product.getProductId());
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            items.put(product.getProductId(), cartItem);
        } else {
            CartItem newCartItem = new CartItem(product, quantity);
            items.put(product.getProductId(), newCartItem);
        }
    }

    public void removeItem(long productId) {
        if (!items.containsKey(productId)) {
            throw new IllegalArgumentException("Cart does not contain a product with ID " + productId + "!");
        }
        items.remove(productId);
    }

    public void updateQuantity(long productId, int quantity) {
        if (!items.containsKey(productId)) {
            throw new IllegalArgumentException("Cart does not contain a product with ID " + productId + "!");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero!");
        }

        CartItem cartItem = items.get(productId);
        cartItem.setQuantity(quantity);
    }

    public double getTotal() {
        double total = 0;

        for (CartItem item : items.values()) {
            total += item.getSubtotal();
        }

        return total;
    }

    public List<CartItem> getItems() {
        List<CartItem> cartItems = new ArrayList<>(items.values());
        return cartItems;
    }

    public void clear() {
        items.clear();
    }
}
