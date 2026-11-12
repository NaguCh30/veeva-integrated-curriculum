package service;

import exceptions.*;
import model.Product;
import repository.ProductRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;
    private int nextProductId = 1001;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(String productName,
                              double productPrice,
                              LocalDate manufacturingDate,
                              int quantityInHand,
                              String companyName) throws Exception {
        Product product = new Product(nextProductId,
                productName,
                productPrice,
                manufacturingDate,
                quantityInHand,
                companyName
        );

        productRepository.addProduct(product);
        nextProductId++;
        return product;
    }

    public Product getProductByProductID(int productId) {
        return productRepository.getProductByProductID(productId);
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public Product updateProduct(int productId, String newName, double newPrice, int newQuantity, String newCompany)
            throws InvalidProductPriceException,
            InvalidProductNameException,
            ProductNotFoundException,
            InvalidCompanyNameException,
            InvalidProductQuantityException {

        return productRepository.updateProduct(productId, newName, newPrice, newQuantity, newCompany);
    }

    public void deleteProductByProductId(int productId) {
        productRepository.deleteProductByProductId(productId);
    }

    public List<Product> getProductsByPriceDescending() {
        return productRepository.getAllProducts()
                .stream()
                .sorted(Comparator.comparing(Product::getProductPrice).reversed())
                .toList();
    }

    public List<Product> getProductsInRangeByPriceDescending(double low, double high) {
        return productRepository.getAllProducts()
                .stream()
                .filter(product -> (product.getProductPrice() >= low && product.getProductPrice() <= high))
                .sorted(Comparator.comparing(Product::getProductPrice).reversed())
                .toList();
    }

    public List<Product> getProductsByPriceAndManufacturingDateDescending() {
        /*
        return productRepository.getAllProducts()
                .stream()
                .sorted((a, b) -> {
                    int res = Double.compare(b.getProductPrice(), a.getProductPrice());
                    if (res != 0) {
                        return res;
                    }
                    return b.getManufacturingDate().compareTo(a.getManufacturingDate());
                })
                .toList();
         */
        return productRepository.getAllProducts()
                .stream()
                .sorted(
                        Comparator.comparingDouble(Product::getProductPrice)
                                .reversed()
                                .thenComparing(
                                        Product::getManufacturingDate,
                                        Comparator.reverseOrder()
                                )
                )
                .toList();
    }

    public List<Product> getProductDetailsByProductName(String name) throws InvalidProductNameException {
        if (name == null || name.isBlank()) {
            throw new InvalidProductNameException(name + " is not a valid product name to search");
        }

        return productRepository.getAllProducts()
                .stream()
                .filter(p -> p.getProductName().equals(name))
                .toList();
    }

    public List<Product> getProductDetailsByManufacturingDate(LocalDate date) throws InvalidManufacturingDate {
        if(date == null) {
            throw new InvalidManufacturingDate("Invalid manufacturing date to search products");
        }

        return productRepository.getAllProducts()
                .stream()
                .filter(p -> date.isEqual(p.getManufacturingDate()))
                .toList();
    }

    public List<Product> getProductsByPrice(double price) throws InvalidProductPriceException {
        if (price <= 0) {
            throw new InvalidProductPriceException("Provide a valid product price to search products");
        }

        return productRepository.getAllProducts()
                .stream()
                .filter(p -> p.getProductPrice() == price)
                .toList();
    }

    public List<Product> getProductsByNameAndCompanyAndPrice(String productName, String companyName, double productPrice)
            throws InvalidProductNameException,
            InvalidCompanyNameException,
            InvalidProductPriceException {

        if (productName == null || productName.isBlank()) {
            throw new InvalidProductNameException("Provide a valid product name to search products");
        }
        if (companyName == null || companyName.isBlank()) {
            throw new InvalidCompanyNameException("Provide a valid company name to search products");
        }
        if (productPrice <= 0) {
            throw new InvalidProductPriceException("Provide a valid price to search products");
        }

        return productRepository.getAllProducts()
                .stream()
                .filter(p ->
                        productName.equalsIgnoreCase(p.getProductName()) &&
                        companyName.equalsIgnoreCase(p.getCompanyName()) &&
                        Double.compare(productPrice, p.getProductPrice()) == 0
                )
                .toList();
    }

    public List<Product> getTopKProductsByPrice(int k) throws InvalidProductPriceException {
        if (k <= 0) {
            throw new InvalidProductPriceException("Provide a valid price and limit to search products.");
        }

        return productRepository.getAllProducts()
                .stream()
                .sorted(Comparator.comparing(Product::getProductPrice).reversed())
                .limit(k)
                .toList();
    }

    public List<Product> getLeastKProductsByPrice(int k) throws InvalidProductPriceException {
        if (k <= 0) {
            throw new InvalidProductPriceException("Provide a valid price and limit to search products.");
        }

        return productRepository.getAllProducts()
                .stream()
                .sorted(Comparator.comparing(Product::getProductPrice))
                .limit(k)
                .toList();
    }
}