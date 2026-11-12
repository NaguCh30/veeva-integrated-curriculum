package model;

import exceptions.*;

import java.time.LocalDate;
import java.util.List;

public class Product {
    private final int productId;
    private String productName;
    private double productPrice;
    private LocalDate manufacturingDate;
    private int quantityInHand;
    private String companyName;



    public Product(int productId,
                   String productName,
                   double productPrice,
                   LocalDate manufacturingDate,
                   int quantityInHand,
                   String companyName) throws Exception {

        if (productName == null || productName.isBlank()) {
            throw new InvalidProductNameException("Invalid Product Name");
        }
        if (productPrice <= 0) {
            throw new InvalidProductPriceException("Invalid Product Price");
        }
        if (manufacturingDate.isAfter(LocalDate.now())) {
            throw new InvalidManufacturingDate("Invalid Manufacturing Date");
        }
        if (quantityInHand < 100) {
            throw new InvalidProductQuantityException("Quantity in Hand should not be less than 100");
        }
        if (companyName == null || companyName.isBlank()) {
            throw new InvalidCompanyNameException("Invalid Company Name");
        }

        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.manufacturingDate = manufacturingDate;
        this.quantityInHand = quantityInHand;
        this.companyName = companyName;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) throws InvalidProductNameException {
        if (productName == null || productName.isBlank()) {
            throw new InvalidProductNameException("Invalid Product Name");
        }
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) throws InvalidProductPriceException {
        if (productPrice <= 0) {
            throw new InvalidProductPriceException("Invalid Product Price");
        }
        this.productPrice = productPrice;
    }

    public int getQuantityInHand() {
        return quantityInHand;
    }

    public void setQuantityInHand(int quantityInHand) throws InvalidProductQuantityException {
        if (quantityInHand < 100) {
            throw new InvalidProductQuantityException("Quantity in Hand should not be less than 100");
        }
        this.quantityInHand = quantityInHand;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) throws InvalidCompanyNameException {
        if (companyName == null || companyName.isBlank()) {
            throw new InvalidCompanyNameException("Invalid Company Name");
        }
        this.companyName = companyName;
    }
}
