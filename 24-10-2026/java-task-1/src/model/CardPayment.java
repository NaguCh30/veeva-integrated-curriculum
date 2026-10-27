package model;

public abstract class CardPayment implements PaymentMethod {
    private final String cardNumber;

    public CardPayment(String cardNumber) {
        if (cardNumber == null || cardNumber.isBlank()) {
            throw new IllegalArgumentException("Invalid Card Number!");
        }
        this.cardNumber = cardNumber;
    }

    @Override
    public abstract boolean pay(double amount);
}
