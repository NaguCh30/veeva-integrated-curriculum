package repository;

import model.Product;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private final Map<String, Product> products = new HashMap<>();

    public Product getProductById(String productId) {
        return products.get(productId);
    }

    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    public void deleteProductById(String productId) {
        products.remove(productId);
    }

    public Map<String, Product> getAllProducts() {
        return Collections.unmodifiableMap(products);
    }
}
