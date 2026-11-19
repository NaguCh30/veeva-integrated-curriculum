package model;

import exception.InvalidProductIdException;
import exception.InvalidProductNameException;
import exception.InvalidProductPriceException;
import exception.InvalidProductQuantityException;

public class Product {
    private int productId;
    private String productName;
    private double productPrice;
    private int availableQuantity;

    public Product(int productId, String productName, double productPrice, int availableQuantity) throws InvalidProductNameException, InvalidProductPriceException, InvalidProductQuantityException, InvalidProductIdException {
        if (productId <= 0) {
            throw new InvalidProductIdException("Product ID should be greater than zero");
        }
        if (productName == null || productName.isBlank()) {
            throw new InvalidProductNameException("Please provide a valid product name.");
        }
        if (productPrice <= 0) {
            throw new InvalidProductPriceException("Please provide a valid price for the product.");
        }
        if (availableQuantity < 0) {
            throw new InvalidProductQuantityException("Product quantity cannot be negative.");
        }
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.availableQuantity = availableQuantity;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setProductName(String productName) throws InvalidProductNameException {
        if (productName == null || productName.isBlank()) {
            throw new InvalidProductNameException("Please provide a valid product name.");
        }
        this.productName = productName;
    }

    public void setProductPrice(double productPrice) throws InvalidProductPriceException {
        if (productPrice <= 0) {
            throw new InvalidProductPriceException("Please provide a valid price for the product.");
        }
        this.productPrice = productPrice;
    }

    public void setAvailableQuantity(int newQuantity) throws InvalidProductQuantityException {
        if (newQuantity < 0) {
            throw new InvalidProductQuantityException("Product quantity cannot be negative.");
        }
        this.availableQuantity = newQuantity;
    }
}
