package com.sg.bankaccountkata.adapter;

import com.sg.bankaccountkata.core.domain.Transaction;
import com.sg.bankaccountkata.core.port.out.TransactionRepositoryOutput;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepositoryFeature implements TransactionRepositoryOutput {

    private List<Transaction> transactions = new ArrayList<>();

    public void saveTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> findAllTransactions() {
        return transactions;
    }
}
