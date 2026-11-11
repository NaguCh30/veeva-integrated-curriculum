package repository;

import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Provide valid product!");
        }
        if (products.containsKey(product.getProductId())) {
            throw new IllegalArgumentException("Product ID " + product.getProductId() + " already exists!");
        }

        products.put(product.getProductId(), product);
    }

    public Product findById(long productId) {
        return products.get(productId);
    }

    public List<Product> findByName(String productName) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Provide a valid product name to search!");
        }
        List<Product> matchingProducts = new ArrayList<>();

        for (Product product : products.values()) {
            if (productName.equalsIgnoreCase(product.getProductName())) {
                matchingProducts.add(product);
            }
        }

        return matchingProducts;
    }

    public List<Product> findByNameAndPrice(String productName, double price) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Provide a valid product name to search!");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("Provide valid price value to search!");
        }

        List<Product> matchingProducts = new ArrayList<>();
        for (Product product: products.values()) {
            if (productName.equalsIgnoreCase(product.getProductName()) && price == product.getPrice()) {
                matchingProducts.add(product);
            }
        }

        return matchingProducts;
    }

    public List<Product> findByNameAndPriceRange(String productName, double minPrice, double maxPrice) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Provide a valid product name to search!");
        }

        if (minPrice > maxPrice) {
            throw new IllegalArgumentException("Minimum Price should be less than Maximum Price!");
        }

        if (maxPrice <= 0 || minPrice <= 0) {
            throw new IllegalArgumentException("Maximum and Minimum prices cannot be zero or negative!");
        }

        List<Product> matchingProducts = new ArrayList<>();
        for (Product product : products.values()) {
            if (productName.equalsIgnoreCase(product.getProductName()) &&
                product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                matchingProducts.add(product);
            }
        }

        return matchingProducts;
    }

    public List<Product> findByNameAndCompany(String productName, String company) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Provide a valid product name!");
        }

        if (company == null || company.isBlank()) {
            throw new IllegalArgumentException("Provide a valid company name!");
        }

        List<Product> matchingProducts = new ArrayList<>();
        for (Product product : products.values()) {
            if (productName.equalsIgnoreCase(product.getProductName()) && company.equalsIgnoreCase(product.getCompany())) {
                matchingProducts.add(product);
            }
        }

        return matchingProducts;
    }

    public List<Product> findByNameAndPriceAndCompany(String productName, double price, String company) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Provide valid product name!");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("Provide valid price!");
        }

        if (company == null || company.isBlank()) {
            throw new IllegalArgumentException("Provide valid company name!");
        }

        List<Product> matchingProducts = new ArrayList<>();
        for (Product product : products.values()) {
            if (productName.equalsIgnoreCase(product.getProductName()) &&
                price == product.getPrice() && company.equalsIgnoreCase(product.getCompany())) {
                matchingProducts.add(product);
            }
        }

        return matchingProducts;
    }

    public void updateProduct(long productId, String newProductName, double newPrice, String newCompany) {
        if (!products.containsKey(productId)) {
            throw new IllegalArgumentException("Product with ID " + productId + " does not exist!");
        }

        if (newProductName == null || newProductName.isBlank()) {
            throw new IllegalArgumentException("Provide valid product name to update!");
        }

        if (newPrice <= 0) {
            throw new IllegalArgumentException("Enter a valid price!");
        }

        if (newCompany == null || newCompany.isBlank()) {
            throw new IllegalArgumentException("Provide a valid company name!");
        }

        Product product = findById(productId);
        product.setProductName(newProductName);
        product.setPrice(newPrice);
        product.setCompany(newCompany);
    }

    public void deleteProduct(long productId) {
        if (!products.containsKey(productId)) {
            throw new IllegalArgumentException("Product with ID " + productId + " does not exist!");
        }

        products.remove(productId);
    }
}
