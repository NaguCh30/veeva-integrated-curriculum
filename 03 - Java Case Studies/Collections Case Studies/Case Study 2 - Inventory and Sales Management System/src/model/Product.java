package model;

import exception.InvalidNameException;
import exception.InvalidPriceException;
import exception.InvalidStockUpdateException;

public class Product {
    private String productId;
    private String productName;
    private double unitPrice;
    private String companyId;
    private ProductCategory productCategory;
    private int availableStock;

    public Product(String productId, String productName, double unitPrice, String companyId, ProductCategory productCategory, int availableStock) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.companyId = companyId;
        this.productCategory = productCategory;
        this.availableStock = availableStock;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public String getCompanyID() {
        return companyId;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductName(String productName) throws InvalidNameException {
        if (productName == null || productName.isBlank()) {
            throw new InvalidNameException("Please provide a valid product name.");
        }
        this.productName = productName;
    }

    public void setUnitPrice(double unitPrice) throws InvalidPriceException {
        if (unitPrice < 0) {
            throw new InvalidPriceException("Please provide valid price for the product");
        }
        this.unitPrice = unitPrice;
    }

    public void setAvailableStock(int stock) throws InvalidStockUpdateException {
        if (stock < 0) {
            throw new InvalidStockUpdateException("Stock must be greater than 0");
        }
        this.availableStock = stock;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
}