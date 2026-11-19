package payment;

import model.PaymentResult;

import java.util.HashMap;
import java.util.Map;

public class CashPayment implements Payment {
    private final double amount;
    private final Map<Integer, Integer> denominations;

    public CashPayment(double amount, Map<Integer, Integer> dennominations) {
        this.amount = amount;
        this.denominations = new HashMap<>(dennominations);
    }

    @Override
    public PaymentResult pay() {
        return new PaymentResult(amount, denominations);
    }
}
