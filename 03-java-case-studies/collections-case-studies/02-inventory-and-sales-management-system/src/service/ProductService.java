package service;

import exception.*;
import model.Company;
import model.Product;
import model.ProductCategory;
import model.ProductInfo;
import repository.CompanyRepository;
import repository.ProductRepository;

public class ProductService {
    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;

    public ProductService(ProductRepository productRepository, CompanyRepository companyRepository) {
        this.productRepository = productRepository;
        this.companyRepository = companyRepository;
    }

    public Product getProductById(String productId) throws InvalidProductIdException, ProductNotFoundException {
        if (productId == null || productId.isBlank()) {
            throw new InvalidProductIdException("Please provide a valid product ID to search");
        }

        Product product = productRepository.getProductById(productId);
        if (product == null) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found.");
        }

        return product;
    }

    public void addProduct(String productId, String productName, double unitPrice, String companyId, ProductCategory productCategory, int availableStock) throws InvalidProductIdException, InvalidNameException, InvalidStockUpdateException, InvalidCompanyIdException, CompanyNotFoundException, DuplicateProductException, InvalidPriceException {
        boolean duplicateName = productRepository
                .getAllProducts()
                .values()
                .stream()
                .anyMatch(product ->
                        product.getProductName().equalsIgnoreCase(productName)
                );
        if (duplicateName) {
            throw new DuplicateProductException("Product with name " + productName + " already exists.");
        }
        if (productId == null || productId.isBlank()) {
            throw new InvalidProductIdException("Please provide a valid Product ID to add the product");
        }
        if (productName == null || productName.isBlank()) {
            throw new InvalidNameException("Please provide a valid Product Name to add the product.");
        }
        if (unitPrice <= 0) {
            throw new InvalidPriceException("Unit price should be greater than 0.");
        }
        if (companyId == null || companyId.isBlank()) {
            throw new InvalidCompanyIdException("Please provide a valid Company ID for the product to add");
        }
        if (availableStock < 0) {
            throw new InvalidStockUpdateException("Please provide a valid stock quantity for the product. Should be greater than 0.");
        }

        Company existingCompany = companyRepository.getCompanyById(companyId);
        if (existingCompany == null) {
            throw new CompanyNotFoundException("Company with ID " + companyId + " not found to add the product.");
        }

        Product existingProduct = productRepository.getProductById(productId);
        if (existingProduct != null) {
            throw new DuplicateProductException("Product with ID " + productId + " already exists!");
        }

        Product product = new Product(productId, productName, unitPrice, companyId, productCategory, availableStock);
        productRepository.addProduct(product);
    }

    public void deleteProductById(String productId) throws InvalidProductIdException, ProductNotFoundException {
        if (productId == null || productId.isBlank()) {
            throw new InvalidProductIdException("Please provide a valid Product ID to delete.");
        }

        Product existingProduct = productRepository.getProductById(productId);
        if (existingProduct == null) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found!");
        }

        productRepository.deleteProductById(productId);
    }

    public void updateProduct(String productId, String productName, double unitPrice, ProductCategory productCategory, int availableStock) throws InvalidProductIdException, InvalidNameException, InvalidStockUpdateException, InvalidCompanyIdException, CompanyNotFoundException, ProductNotFoundException, InvalidPriceException {
        if (productId == null || productId.isBlank()) {
            throw new InvalidProductIdException("Please provide a valid Product ID to update the product");
        }
        if (productName == null || productName.isBlank()) {
            throw new InvalidNameException("Please provide a valid Product Name to update the product.");
        }
        if (unitPrice <= 0) {
            throw new InvalidPriceException("Unit price should be greater than 0.");
        }
        if (availableStock < 0) {
            throw new InvalidStockUpdateException("Please provide a valid stock quantity for the product. Should be greater than 0.");
        }

        Product existingProduct = productRepository.getProductById(productId);
        if (existingProduct == null) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found to update!");
        }

        existingProduct.setProductName(productName);
        existingProduct.setAvailableStock(availableStock);
        existingProduct.setProductCategory(productCategory);
        existingProduct.setUnitPrice(unitPrice);
    }

    public ProductInfo getPriceDetailsByProductId(String productId) throws InvalidProductIdException, ProductNotFoundException, CompanyNotFoundException {
        if (productId == null || productId.isBlank()) {
            throw new InvalidProductIdException("Please provide a valid Product ID to search details");
        }

        Product product = productRepository.getProductById(productId);
        if (product == null) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found.");
        }

        Company company = companyRepository.getCompanyById(product.getCompanyID());
        if (company == null) {
            throw new CompanyNotFoundException("Company details for the product " + product.getProductName() + " not found!");
        }

        ProductInfo info = new ProductInfo(
                product.getProductId(),
                product.getProductName(),
                product.getUnitPrice(),
                product.getProductCategory(),
                product.getAvailableStock(),
                company.getCompanyId(),
                company.getCompanyName(),
                company.getCompanyContact(),
                company.getCompanyLocation()
        );

        return info;
    }
}
