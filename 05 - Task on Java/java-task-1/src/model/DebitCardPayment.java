package model;

public class DebitCardPayment extends CardPayment {

    public DebitCardPayment(String cardNumber) {
        super(cardNumber);
    }

    @Override
    public boolean pay(double amount) {
        if (amount <= 0 || Double.isInfinite(amount) || Double.isNaN(amount)) {
            throw new IllegalArgumentException("Invalid Amount!");
        }
        return true;
    }

}
