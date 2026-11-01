package model;

public interface PaymentMethod {
    boolean pay(double amount);

    default boolean isDeferredPayment() {
        return false;
    }
}
