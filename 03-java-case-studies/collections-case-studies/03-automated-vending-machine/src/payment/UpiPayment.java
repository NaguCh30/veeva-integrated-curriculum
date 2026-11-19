package payment;

import model.PaymentResult;

import java.util.Collections;
import java.util.Map;

public class UpiPayment implements Payment {
    private final double amount;
    private final Map<Integer, Integer> denominations;

    public UpiPayment(double amount) {
        this.amount = amount;
        this.denominations = Collections.emptyMap();
    }

    @Override
    public PaymentResult pay() {
        return new PaymentResult(amount, denominations);
    }
}
