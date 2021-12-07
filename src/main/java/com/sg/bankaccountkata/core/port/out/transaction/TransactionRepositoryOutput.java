package com.sg.bankaccountkata.core.port.out.transaction;

import com.sg.bankaccountkata.core.domain.Transaction;

import java.util.List;

public interface TransactionRepositoryOutput {

    void saveTransaction(Transaction transaction);

    List<Transaction> findAllTransactions();
}
