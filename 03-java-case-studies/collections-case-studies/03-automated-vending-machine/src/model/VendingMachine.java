package model;

import exception.InvalidProductsException;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    private Map<Integer, Integer> cashBalance;
    private double upiBalance = 0.0;

    public VendingMachine() {
        cashBalance = new HashMap<>();

        cashBalance.put(500, 0);
        cashBalance.put(200, 0);
        cashBalance.put(100, 0);
        cashBalance.put(50, 0);
        cashBalance.put(20, 0);
        cashBalance.put(10, 0);
        cashBalance.put(5, 0);
        cashBalance.put(2, 0);
        cashBalance.put(1, 0);
    }

    public double getTotalAmount() {
        double cashTotal = cashBalance.entrySet()
                .stream()
                .mapToDouble(entry -> entry.getValue() * entry.getKey())
                .sum();
        return cashTotal + upiBalance;
    }

    public Map<Integer, Integer> getCashBalance() {
        return cashBalance;
    }

    public double getUpiBalance() {
        return upiBalance;
    }


    public void setCashBalanceToZero() {
        cashBalance.replaceAll((denomination, quantity) -> 0);
    }

    public void setUpiBalance(double upiBalance) {
        this.upiBalance = upiBalance;
    }

    public void addUpiBalance(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }

        this.upiBalance += amount;
    }
}
