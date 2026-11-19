package service;

import exception.*;
import model.*;
import payment.CashPayment;
import payment.Payment;
import payment.UpiPayment;
import repository.ProductRepository;

import java.util.*;

public class VendingMachineService {

    private static final int MAX_PRODUCTS = 12;

    private final ProductRepository productRepository;
    private final VendingMachine vendingMachine;

    public VendingMachineService(ProductRepository productRepository, VendingMachine vendingMachine) {
        this.productRepository = productRepository;
        this.vendingMachine = vendingMachine;
    }

    // admin can add the product into vending machine
    public void addProduct(int id, String name, double price, int quantity) throws InvalidProductIdException, InvalidProductPriceException, InvalidProductQuantityException, InvalidProductNameException, DuplicateProductException, InvalidProductsException {
        if (id <= 0) {
            throw new InvalidProductIdException("Product ID should be greater than zero.");
        }
        if (name == null || name.isBlank()) {
            throw new InvalidProductNameException("Please provide a valid product name.");
        }
        if (price <= 0) {
            throw new InvalidProductPriceException("Please provide a valid price for the product.");
        }
        if (quantity < 0) {
            throw new InvalidProductQuantityException("Product quantity cannot be negative.");
        }

        Product existingProduct = productRepository.getProductById(id);
        if (existingProduct != null) {
            throw new DuplicateProductException("Product with ID " + id + " already exists in the vending machine!");
        }

        boolean existsWithName = productRepository.getAllProducts()
                .stream()
                .anyMatch(product ->  product.getProductName().equalsIgnoreCase(name));
        if (existsWithName) {
            throw new DuplicateProductException("Product with name '" + name + "' already exists in the vending machine!");
        }

        if (productRepository.getAllProducts().size() >= MAX_PRODUCTS) {
            throw new InvalidProductsException("The vending machine can contain a maximum of 12 products.");
        }

        Product product = new Product(id, name, price, quantity);

        productRepository.addProduct(product);

    }

    // both admin and user can get product by its id
    public Product getProductById(int id) throws InvalidProductIdException, ProductNotFoundException {
        if (id <= 0) {
            throw new InvalidProductIdException("Product ID should be greater than zero.");
        }

        Product existingProduct = productRepository.getProductById(id);
        if (existingProduct == null) {
            throw new ProductNotFoundException("There is no product with ID " + id + " in the vending machine.");
        }

        return existingProduct;
    }

    // admin and system action (can update the quantity)
    public void updateProductById(int id, String newName, double newPrice, int newQuantity) throws InvalidProductIdException, InvalidProductNameException, InvalidProductPriceException, InvalidProductQuantityException, ProductNotFoundException, DuplicateProductException {
        if (id <= 0) {
            throw new InvalidProductIdException("Product ID should be greater than zero.");
        }
        if (newName == null || newName.isBlank()) {
            throw new InvalidProductNameException("Please provide a valid product name.");
        }
        if (newPrice <= 0) {
            throw new InvalidProductPriceException("Please provide a valid price for the product.");
        }
        if (newQuantity < 0) {
            throw new InvalidProductQuantityException("Product quantity cannot be negative.");
        }

        boolean duplicateName = productRepository.getAllProducts()
                .stream()
                .anyMatch(
                        existing ->
                                existing.getProductId() != id &&
                                existing.getProductName().equalsIgnoreCase(newName)
                );
        if (duplicateName) {
            throw new DuplicateProductException("Product with name '" + newName + "' already exists in the vending machine!");
        }

        Product product = getProductById(id);

        product.setProductName(newName);
        product.setProductPrice(newPrice);
        product.setAvailableQuantity(newQuantity);

        productRepository.updateProduct(product);
    }

    // admin and user can see all available products
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    // admin can remove product by id
    public void removeProductById(int id) throws InvalidProductIdException, ProductNotFoundException {
        if (id <= 0) {
            throw new InvalidProductIdException("Product ID should be greater than zero.");
        }

        Product existingProduct = productRepository.getProductById(id);
        if (existingProduct == null) {
            throw new ProductNotFoundException("Product with ID " + id + " does not exist in the vending machine.");
        }

        productRepository.removeProductById(id);
    }


    // admin and user can see available cash denominations
    public Map<Integer, Integer> getAvailableDenominations() {
        return Collections.unmodifiableMap(new HashMap<>(vendingMachine.getCashBalance()));
    }

    // admin can see available cash money
    public double getAvailableCashMoney() {
        return vendingMachine.getCashBalance().entrySet()
                .stream()
                .mapToDouble(entry -> entry.getKey() * entry.getValue())
                .sum();
    }

    // admin can see available upi cash
    public double getAvailableUpiMoney() {
        return vendingMachine.getUpiBalance();
    }

    // admin can see total available money
    public double getTotalAvailableMoney() {
        return getAvailableCashMoney() + getAvailableUpiMoney();
    }

    // --------------------------------------------------------------------------------------------------------
    // Collecting money

    // admin can collect all the total money
    public double collectTotalMoney() {
        double totalBalance = getTotalAvailableMoney();
        vendingMachine.setCashBalanceToZero();
        vendingMachine.setUpiBalance(0.0);

        return totalBalance;
    }

    // admin can collect only the cash money
    public double collectCashMoney() {
        double cashMoney = getAvailableCashMoney();
        vendingMachine.setCashBalanceToZero();
        return cashMoney;
    }

    // admin can collect only upi money
    public double collectUpiMoney() {
        double upiMoney = getAvailableUpiMoney();
        vendingMachine.setUpiBalance(0.0);
        return upiMoney;
    }

    // admin can add or load cash into the vending machine
    public void addCashInventory(Map<Integer, Integer> denominations) throws InvlaidDenominationsException {
        if (denominations == null || denominations.isEmpty()) {
            throw new InvlaidDenominationsException("Please provide valid cash denominations");
        }

        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            Integer denomination = entry.getKey();
            Integer quantity = entry.getValue();

            if (denomination == null || !vendingMachine.getCashBalance().containsKey(denomination)) {
                throw new InvlaidDenominationsException("Unsupported denomination: " + denomination);
            }

            if (quantity == null || quantity < 0) {
                throw new InvlaidDenominationsException("Invalid quantity for denomination: " + denomination);
            }
        }

        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            vendingMachine.getCashBalance().merge(
                    entry.getKey(),
                    entry.getValue(),
                    Integer::sum
            );
        }
    }


    // processing payments
    public PaymentResult processCashPayment(Map<Integer, Integer> denominations) throws InvalidAmountException, InvlaidDenominationsException {
        if (denominations == null || denominations.isEmpty()) {
            throw new InvlaidDenominationsException("Please provide valid denominations.");
        }

        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            if (!vendingMachine.getCashBalance().containsKey(entry.getKey())) {
                throw new InvlaidDenominationsException("Unsupported denomination: " + entry.getKey());
            }
            if (entry.getValue() == null || entry.getValue() <= 0) {
                throw new InvlaidDenominationsException("Invalid quantity for denomination: " + entry.getKey());
            }
        }

        double amount = denominations.entrySet()
                .stream()
                .mapToDouble(entry -> entry.getKey() * entry.getValue())
                .sum();

        if (amount <= 0) {
            throw new InvalidAmountException("Please enter valid amount!");
        }

        Payment payment = new CashPayment(amount, denominations);

        return payment.pay();
    }

    // processing upi payment
    public PaymentResult processUpiPayment(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Please provide valid amount.");
        }

        Payment payment = new UpiPayment(amount);

        return payment.pay();
    }



    // user action
    // purchasing products by paying
    public Purchase purchaseProducts(
            Map<Integer, Integer> selectedProducts,
            PaymentMethod paymentMethod,
            double amountPaid,
            Map<Integer, Integer> denominations)
            throws InsufficientFundsException,
            InsufficientChangeException,
            InsufficientQuantityException,
            SoldOutException,
            ProductNotFoundException,
            InvlaidDenominationsException,
            InvalidAmountException,
            InvalidProductQuantityException {

        // 1. Validate selected products and their quantities.
        validateProductAvailability(selectedProducts);

        // 2. Calculate the total purchase cost.
        double totalCost = calculateTotalCost(selectedProducts);

        // 3. Validate the payment method.
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Invalid payment method.");
        }

        PaymentResult paymentResult;
        Map<Integer, Integer> changeDenominations = new HashMap<>();

        // 4. Process payment based on the selected payment method.
        if (paymentMethod == PaymentMethod.CASH) {

            if (denominations == null || denominations.isEmpty()) {
                throw new IllegalArgumentException("Please provide cash denominations.");
            }

            paymentResult = processCashPayment(denominations);

            // Ensure the supplied amount matches the denominations.
            if (Double.compare(amountPaid, paymentResult.getAmount()) != 0) {
                throw new IllegalArgumentException("Amount paid does not match the inserted denominations.");
            }

            // 5. Validate whether the customer has paid enough.
            validatePayment(totalCost, paymentResult);

            // 6. Calculate the change required.
            double changeAmount = amountPaid - totalCost;

            // 7. Find the denominations required to return the change.
            changeDenominations = calculateChange(changeAmount, denominations
            );

        } else if (paymentMethod == PaymentMethod.UPI) {

            if (denominations != null && !denominations.isEmpty()) {
                throw new IllegalArgumentException("Denominations are not applicable for UPI payment.");
            }

            paymentResult = processUpiPayment(amountPaid);

            // UPI requires exact payment.
            if (Double.compare(amountPaid, totalCost) != 0) {
                throw new IllegalArgumentException(
                        "Please pay the exact purchase amount using UPI.");
            }
        }

        // 8. Update the cash inventory for cash payments.
        if (paymentMethod == PaymentMethod.CASH) {
            updateCashInventory(denominations, changeDenominations);
        } else if (paymentMethod == PaymentMethod.UPI) {
            vendingMachine.addUpiBalance(amountPaid);
        }

        // 9. Decrease the quantities of purchased products.
        for (Map.Entry<Integer, Integer> entry : selectedProducts.entrySet()) {
            Product product = productRepository.getProductById(entry.getKey());
            int updatedQuantity = product.getAvailableQuantity() - entry.getValue();
            product.setAvailableQuantity(updatedQuantity);
            productRepository.updateProduct(product);
        }

        // 10. Calculate the change amount.
        double changeReturned = amountPaid - totalCost;

        // 11. Create the final purchase receipt.
        return new Purchase(
                selectedProducts,
                totalCost,
                paymentMethod,
                amountPaid,
                changeReturned
        );
    }





    // utility methods

    private void validateProductAvailability(
            Map<Integer, Integer> selectedProducts) throws ProductNotFoundException, SoldOutException, InsufficientQuantityException {

        if (selectedProducts == null || selectedProducts.isEmpty()) {
            throw new IllegalArgumentException(
                    "Please select at least one product."
            );
        }

        for (Map.Entry<Integer, Integer> entry : selectedProducts.entrySet()) {

            if (entry.getKey() == null || entry.getValue() == null) {
                throw new IllegalArgumentException(
                        "Product ID and requested quantity cannot be null."
                );
            }

            int productId = entry.getKey();
            int requestedQuantity = entry.getValue();

            if (productId <= 0) {
                throw new IllegalArgumentException("Invalid product ID.");
            }

            if (requestedQuantity <= 0) {
                throw new IllegalArgumentException("Requested quantity must be greater than zero.");
            }

            Product product = productRepository.getProductById(productId);

            if (product == null) {
                throw new ProductNotFoundException("Product with ID " + productId + " does not exist.");
            }

            int availableQuantity = product.getAvailableQuantity();

            if (availableQuantity == 0) {
                throw new SoldOutException(product.getProductName() + " is sold out.");
            }

            if (requestedQuantity > availableQuantity) {
                throw new InsufficientQuantityException("Only " + availableQuantity + " units of " + product.getProductName() + " are available.");
            }
        }
    }


    private double calculateTotalCost(
            Map<Integer, Integer> selectedProducts) {

        return selectedProducts.entrySet()
                .stream()
                .mapToDouble(entry -> {
                    Product product = productRepository.getProductById(entry.getKey());

                    return product.getProductPrice() * entry.getValue();
                })
                .sum();
    }

    private void validatePayment(double totalCost, PaymentResult paymentResult)
            throws InsufficientFundsException {
        double amountPaid = paymentResult.getAmount();

        if (amountPaid < totalCost) {
            throw new InsufficientFundsException("Insufficient funds! Please pay additional " + (totalCost - amountPaid) + " rupees!");
        }
    }


    private Map<Integer, Integer> calculateChange(double changeAmount, Map<Integer, Integer> incomingDenominations)
            throws InsufficientChangeException {

        int remainingAmount = (int) Math.round(changeAmount);

        if (remainingAmount < 0) {
            throw new IllegalArgumentException("Invalid change amount.");
        }

        if (remainingAmount == 0) {
            return new HashMap<>();
        }

        Map<Integer, Integer> availableCash = new HashMap<>(vendingMachine.getCashBalance());

        for (Map.Entry<Integer, Integer> entry : incomingDenominations.entrySet()) {
            availableCash.merge(
                    entry.getKey(),
                    entry.getValue(),
                    Integer::sum
            );
        }

        List<Integer> denominations = availableCash.keySet()
                .stream()
                .sorted(Comparator.reverseOrder())
                .toList();

        Map<Integer, Integer> change = new HashMap<>();

        boolean possible = findChange(
                denominations,
                availableCash,
                0,
                remainingAmount,
                change
        );

        if (!possible) {
            throw new InsufficientChangeException("Sorry! The machine cannot provide the required change.");
        }

        return change;
    }


    private boolean findChange(
            List<Integer> denominations,
            Map<Integer, Integer> availableCash,
            int index,
            int remainingAmount,
            Map<Integer, Integer> change) {

        if (remainingAmount == 0) {
            return true;
        }

        if (index >= denominations.size()) {
            return false;
        }

        int denomination = denominations.get(index);
        int availableQuantity = availableCash.get(denomination);

        int maxQuantity = Math.min(
                remainingAmount / denomination,
                availableQuantity
        );

        for (int quantity = maxQuantity; quantity >= 0; quantity--) {

            int newRemainingAmount =
                    remainingAmount - (denomination * quantity);

            if (quantity > 0) {
                change.put(denomination, quantity);
            } else {
                change.remove(denomination);
            }

            if (findChange(
                    denominations,
                    availableCash,
                    index + 1,
                    newRemainingAmount,
                    change)) {

                return true;
            }
        }

        change.remove(denomination);
        return false;
    }

    private void updateCashInventory(
            Map<Integer, Integer> incomingDenominations,
            Map<Integer, Integer> changeDenominations) {

        Map<Integer, Integer> originalCash =
                vendingMachine.getCashBalance();

        // Create a temporary copy of the original inventory.
        Map<Integer, Integer> updatedCash =
                new HashMap<>(originalCash);

        // 1. Add the customer's incoming cash.
        for (Map.Entry<Integer, Integer> entry : incomingDenominations.entrySet()) {
            updatedCash.merge(
                    entry.getKey(),
                    entry.getValue(),
                    Integer::sum
            );
        }

        // 2. Subtract the change returned to the customer.
        for (Map.Entry<Integer, Integer> entry : changeDenominations.entrySet()) {
            int denomination = entry.getKey();
            int quantity = entry.getValue();

            int availableQuantity = updatedCash.getOrDefault(denomination, 0);

            if (quantity > availableQuantity) {
                throw new IllegalStateException("Invalid change calculation.");
            }

            updatedCash.put(denomination, availableQuantity - quantity);
        }

        // 3. Commit the updated inventory to the original map.
        originalCash.clear();
        originalCash.putAll(updatedCash);
    }

}
