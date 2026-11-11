package model;

public class GiftCardPayment implements PaymentMethod {
    private final String giftCardNumber;
    private double balance;

    public GiftCardPayment(String giftCardNumber, double balance) {
        if (giftCardNumber == null || giftCardNumber.isBlank()) {
            throw new IllegalArgumentException("Provide valid Gift Card Number");
        }
        if (balance < 0 || !Double.isFinite(balance)) {
            throw new IllegalArgumentException("Invalid Balance Amount!");
        }
        this.giftCardNumber = giftCardNumber;
        this.balance = balance;
    }

    @Override
    public boolean pay(double amount) {
        if (amount <= 0 || !Double.isFinite(amount)) {
            throw new IllegalArgumentException("Invalid Amount!");
        }
        if (balance < amount) {
            return false;
        }

        balance = balance - amount;
        return true;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0 || !Double.isFinite(balance)) {
            throw new IllegalArgumentException("Invalid balance!");
        }
        this.balance = balance;
    }

    public String getGiftCardNumber() {
        return giftCardNumber;
    }
}
