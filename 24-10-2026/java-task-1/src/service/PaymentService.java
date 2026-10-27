package service;

import model.PaymentMethod;

public class PaymentService {
    public boolean processPayment(PaymentMethod paymentMethod, double amount) {
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Select Valid Payment Method!");
        }
        if (amount <= 0 || !Double.isFinite(amount)) {
            throw new IllegalArgumentException("Invalid Amount!");
        }

        return paymentMethod.pay(amount);
    }
}
