package repository;

import model.Product;

import java.util.*;


public class ProductRepository {
    private final Map<Integer, Product> products = new HashMap<>();

    public Product getProductById(int id) {
        return products.get(id);
    }

    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(new ArrayList<>(products.values()));
    }

    public void updateProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    public void removeProductById(int id) {
        products.remove(id);
    }
}
