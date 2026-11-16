
package repository;

import model.Transaction;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TransactionRepository {

    private final Map<String, Transaction> transactions = new HashMap<>();

    public void addTransaction(Transaction transaction) {
        transactions.put(transaction.getTransactionId(), transaction);
    }

    public Transaction getTransactionById(String transactionId) {
        return transactions.get(transactionId);
    }

    public Map<String, Transaction> getAllTransactions() {
        return Collections.unmodifiableMap(transactions);
    }

}
