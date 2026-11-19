package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Purchase {

    private final Map<Integer, Integer> selectedProducts;
    private final double totalCost;
    private final PaymentMethod paymentMethod;
    private final double amountPaid;
    private final double changeReturned;

    public Purchase(
            Map<Integer, Integer> selectedProducts,
            double totalCost,
            PaymentMethod paymentMethod,
            double amountPaid,
            double changeReturned) {

        this.selectedProducts = new HashMap<>(selectedProducts);
        this.totalCost = totalCost;
        this.paymentMethod = paymentMethod;
        this.amountPaid = amountPaid;
        this.changeReturned = changeReturned;
    }

    public Map<Integer, Integer> getSelectedProducts() {
        return Collections.unmodifiableMap(selectedProducts);
    }

    public double getTotalCost() {
        return totalCost;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getChangeReturned() {
        return changeReturned;
    }
}
