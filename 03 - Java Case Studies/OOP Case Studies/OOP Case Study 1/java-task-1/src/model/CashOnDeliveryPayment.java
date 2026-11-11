package model;

public class CashOnDeliveryPayment implements PaymentMethod {
    @Override
    public boolean pay(double amount) {
        if (amount <= 0 || !Double.isFinite(amount)) {
            throw new IllegalArgumentException("Invalid Amount!");
        }

        return true;
    }

    @Override
    public boolean isDeferredPayment() {
        return true;
    }
}
