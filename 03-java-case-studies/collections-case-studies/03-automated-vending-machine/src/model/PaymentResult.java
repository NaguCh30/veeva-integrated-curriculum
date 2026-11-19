package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PaymentResult {

    private final double amount;
    private final Map<Integer, Integer> denominations;

    public PaymentResult(double amount, Map<Integer, Integer> denominations) {
        this.amount = amount;
        this.denominations = new HashMap<>(denominations);
    }

    public double getAmount() {
        return amount;
    }

    public Map<Integer, Integer> getDenominations() {
        return Collections.unmodifiableMap(denominations);
    }
}
