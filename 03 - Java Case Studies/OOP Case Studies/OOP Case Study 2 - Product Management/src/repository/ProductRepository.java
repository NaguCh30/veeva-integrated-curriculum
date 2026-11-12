package repository;

import exceptions.*;
import model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    final HashMap<Integer, Product> products = new HashMap<>();

    public Product addProduct(Product product) throws DuplicateProductException {
        int productId = product.getProductId();

        Product duplicateProduct = products.get(productId);
        if (duplicateProduct != null) {
            throw new DuplicateProductException("Product with ID " + productId + "already exists!");
        }
        products.put(productId, product);
        return products.get(productId);
    }

    public void deleteProductByProductId(int productId) {
        Optional<Product> product = products.values()
                .stream()
                .filter(p -> p.getProductId() == productId)
                .findFirst();

        if (product.isPresent()) {
            products.remove(productId);
        }
    }

    public Product getProductByProductID(int productId) {
        return products.get(productId);
    }

    public List<Product> getAllProducts() {
        return products.values().stream().toList();
    }

    public Product updateProduct(int productId, String newName, double newPrice, int newQuantity, String newCompany)
            throws ProductNotFoundException,
            InvalidProductNameException,
            InvalidCompanyNameException,
            InvalidProductPriceException,
            InvalidProductQuantityException {

        Product existingProduct = products.get(productId);
        if (existingProduct == null) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found");
        }
        if (newName == null || newName.isBlank()) {
            throw new InvalidProductNameException("Invalid product name to update.");
        }
        if (newPrice <= 0) {
            throw new InvalidProductPriceException("Invalid product price to update.");
        }
        if (newQuantity <= 0) {
            throw new InvalidProductQuantityException("Invalid product quantity. Quantity should be greater than zero.");
        }
        if (newCompany == null || newCompany.isBlank()) {
            throw new InvalidCompanyNameException("Invalid compnay name to update");
        }

        existingProduct.setProductName(newName);
        existingProduct.setProductPrice(newPrice);
        existingProduct.setQuantityInHand(newQuantity);
        existingProduct.setCompanyName(newCompany);

        return existingProduct;
    }
}
