import java.util.List;

class Product {
    private int productId;
    private String productName;
    private double price;
    private List<String> tags;

    public Product(int productId, String productName, double price, List<String> tags) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.tags = tags;
    }

    public int getProductId() { return productId; }
    public String getProductName() { return productName; }
    public double getPrice() { return price; }
    public List<String> getTags() { return tags; }

    @Override
    public String toString() {
        return productId + " | " + productName + " | " + price + " | " + tags;
    }
}

class Category {
    private String categoryName;
    private List<Product> products;

    public Category(String categoryName, List<Product> products) {
        this.categoryName = categoryName;
        this.products = products;
    }

    public String getCategoryName() { return categoryName; }
    public List<Product> getProducts() { return products; }

    @Override
    public String toString() {
        return categoryName + " | " + products;
    }
}


class Main {
    public static void main(String[] args) {
        List<Category> categories = List.of(
            new Category("Electronics", List.of(
                new Product(1, "Laptop", 60000, List.of("Technology", "Work")),
                new Product(2, "Mobile", 25000, List.of("Technology", "Communication"))
            )),
            new Category("Furniture", List.of(
                new Product(3, "Chair", 3000, List.of("Home", "Office")),
                new Product(4, "Table", 7000, List.of("Home", "Office"))
            ))
        );

        System.out.println("Extract all category names.");
        categories.stream()
            .map(Category::getCategoryName)
            .forEach(System.out::println);

        System.out.println("\nExtract all products from all categories into one stream using flatMap().");
        categories.stream()
            .map(Category::getProducts)
            .flatMap(List::stream)
            .forEach(System.out::println);

        System.out.println("\nExtract all product names.");
        categories.stream()
            .map(Category::getProducts)
            .flatMap(List::stream)
            .map(Product::getProductName)
            .forEach(System.out::println);


        System.out.println("\nCalculate the total inventory value using flatMap() and mapToDouble().");
        double totalValue = categories.stream()
            .map(Category::getProducts)
            .flatMap(List::stream)
            .mapToDouble(Product::getPrice)
            .sum();
        System.out.println("Total Inventory Value: " + totalValue);

        System.out.println("\nExtract all product tags across all categories into a single stream.");
        categories.stream()
            .map(Category::getProducts)
            .flatMap(List::stream)
            .map(Product::getTags)
            .flatMap(List::stream)
            .forEach(System.out::println);

        System.out.println("\nFind all distinct tags using distinct().");
        categories.stream()
            .map(Category::getProducts)
            .flatMap(List::stream)
            .map(Product::getTags)
            .flatMap(List::stream)
            .distinct()
            .forEach(System.out::println);

        System.out.println("\nFind products whose price is greater than 5,000 and return their names.");
        categories.stream()
            .flatMap(c -> c.getProducts()
                     .stream())
            .filter(p -> p.getPrice() > 5000)
            .forEach(p -> System.out.println(p.getProductName()));

        System.out.println("\nFind the average product price across all categories.");
        double averagePrice = categories.stream()
            .map(Category::getProducts)
            .flatMap(List::stream)
            .mapToDouble(Product::getPrice)
            .average()
            .orElse(0.0);
        System.out.println("Average Product Price: " + averagePrice);
    }
}