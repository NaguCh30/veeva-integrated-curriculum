package model;

public class UpiPayment implements PaymentMethod {
    private final String upiId;

    public UpiPayment(String upiId) {
        if (upiId == null || upiId.isBlank()) {
            throw new IllegalArgumentException("Provide a valid UPI ID");
        }
        this.upiId = upiId;
    }

    @Override
    public boolean pay(double amount) {
        if (amount <= 0 || Double.isInfinite(amount) || Double.isNaN(amount)) {
            throw new IllegalArgumentException("Invalid Amount!");
        }
        return true;
    }
}
