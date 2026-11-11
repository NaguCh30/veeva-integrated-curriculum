package MethodReferences;

class Product {
    int id;
    String name;
    int price;

    Product (int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
        System.out.println("Product created successfully.");
    }

    void display() {
        System.out.println(id + " - " + name + " - " + price);
    }
}


@FunctionalInterface 
interface ProductCreator {
    Product create(int id, String name, int price);
}


public class ConstructorReference {
    public static void main(String[] args) {
        ProductCreator creator = Product::new;

        Product p1 = creator.create(1, "Laptop", 50000);
        p1.display();
        Product p2 = creator.create(2, "Mobile", 20000);
        p2.display();
    }
}
