package model;

public class Product {
    private final long productId;
    private String productName;
    private double price;
    private String company;

    public Product(long productId, String productName, double price, String company) {

        if (productId <= 0) {
            throw new IllegalArgumentException("Provide a valid Product ID");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("Provide valid product price!");
        }

        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Provide valid product name!");
        }

        if (company == null || company.isBlank()) {
            throw new IllegalArgumentException("Provide valid company name!");
        }

        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.company = company;
    }

    public long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public String getCompany() {
        return company;
    }

    public void setProductName(String productName) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Provide valid product name!");
        }
        this.productName = productName;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Provide valid price!");
        }
        this.price = price;
    }

    public void setCompany(String company) {
        if (company == null || company.isBlank()) {
            throw new IllegalArgumentException("Provide valid company name!");
        }
        this.company = company;
    }
}
