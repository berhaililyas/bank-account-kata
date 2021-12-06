package com.sg.bankaccountkata.core.feature;

import com.sg.bankaccountkata.core.domain.Transaction;
import com.sg.bankaccountkata.core.domain.TransactionType;
import com.sg.bankaccountkata.core.port.in.DepositInputPort;
import com.sg.bankaccountkata.core.port.out.TransactionRepositoryOutput;

import java.time.LocalDate;

public class DepositFeature implements DepositInputPort {

    private final TransactionRepositoryOutput transactionRepositoryOutput;

    public DepositFeature(TransactionRepositoryOutput transactionRepositoryFeature) {
        this.transactionRepositoryOutput = transactionRepositoryFeature;
    }

    public void deposit(int depositAmount) {
        final int currentBalance = transactionRepositoryOutput.findAllTransactions().stream()
                .mapToInt(Transaction::getAmount)
                .sum();

        Transaction transaction = new Transaction(
                TransactionType.DEPOSIT,
                LocalDate.now(),
                depositAmount,
                currentBalance + depositAmount);

        transactionRepositoryOutput.saveTransaction(transaction);
    }
}
