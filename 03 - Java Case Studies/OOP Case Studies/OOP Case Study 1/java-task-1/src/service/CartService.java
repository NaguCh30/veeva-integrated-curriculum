package service;

import model.Product;
import model.ShoppingCart;
import repository.CartRepository;
import repository.CustomerRepository;
import repository.ProductRepository;

public class CartService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public CartService(
            CustomerRepository customerRepository,
            ProductRepository productRepository,
            CartRepository cartRepository) {

        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    public void addItem(long customerId, long productId, int quantity) {
        if (customerId <= 0) {
            throw new IllegalArgumentException("Customer ID should be greater than zero!");
        }
        if(customerRepository.findById(customerId) == null) {
            throw new IllegalArgumentException("Customer with ID " + customerId + " does not exist!");
        }
        if (productId <= 0) {
            throw new IllegalArgumentException("Product ID should be greater than zero!");
        }
        Product product = productRepository.findById(productId);
        if(product == null) {
            throw new IllegalArgumentException("Product with ID " + productId + " does not exist!");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity should be greater than zero!");
        }
        ShoppingCart cart = cartRepository.getShoppingCart(customerId);
        cart.addItem(product, quantity);
    }

    public void removeItem(long customerId, long productId) {
        if (customerId <= 0) {
            throw new IllegalArgumentException("Customer ID should be greater than zero!");
        }
        if(customerRepository.findById(customerId) == null) {
            throw new IllegalArgumentException("Customer with ID " + customerId + " does not exist!");
        }
        if (productId <= 0) {
            throw new IllegalArgumentException("Product ID should be greater than zero!");
        }
        ShoppingCart cart = cartRepository.getShoppingCart(customerId);
        cart.removeItem(productId);
    }

    public void updateQuantity(long customerId, long productId, int quantity) {
        if (customerId <= 0) {
            throw new IllegalArgumentException("Customer ID should be greater than zero!");
        }
        if(customerRepository.findById(customerId) == null) {
            throw new IllegalArgumentException("Customer with ID " + customerId + " does not exist!");
        }
        if (productId <= 0) {
            throw new IllegalArgumentException("Product ID should be greater than zero!");
        }
        cartRepository.getShoppingCart(customerId).updateQuantity(productId, quantity);
    }

    public ShoppingCart getCart(long customerId) {
        if (customerId <= 0) {
            throw new IllegalArgumentException("Customer ID should be greater than zero!");
        }
        if(customerRepository.findById(customerId) == null) {
            throw new IllegalArgumentException("Customer with ID " + customerId + " does not exist!");
        }
        return cartRepository.getShoppingCart(customerId);
    }
}