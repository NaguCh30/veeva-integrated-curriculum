package model;

import java.time.LocalDateTime;
import java.util.List;

public class Transaction {

    private String transactionId;
    private String orderId;
    private List<OrderItem> soldItems;
    private double totalAmount;
    private LocalDateTime transactionDate;

    public Transaction(
            String transactionId,
            String orderId,
            List<OrderItem> soldItems,
            double totalAmount,
            LocalDateTime transactionDate) {

        this.transactionId = transactionId;
        this.orderId = orderId;
        this.soldItems = soldItems;
        this.totalAmount = totalAmount;
        this.transactionDate = transactionDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<OrderItem> getSoldItems() {
        return soldItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    @Override
    public String toString() {
        return
                "========== Transaction Details ==========" +
                        "\nTransaction ID: " + transactionId +
                        "\nOrder ID:       " + orderId +
                        "\nTotal Amount:   " + totalAmount +
                        "\nTransaction Date: " + transactionDate;
    }
}
