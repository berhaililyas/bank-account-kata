package com.sg.bankaccountkata.core.feature;

import com.sg.bankaccountkata.core.domain.Transaction;
import com.sg.bankaccountkata.core.domain.TransactionType;
import com.sg.bankaccountkata.core.port.in.WithdrawlInputPort;
import com.sg.bankaccountkata.core.port.out.TransactionRepositoryOutput;

import java.time.LocalDate;

public class WithdrawlFeature implements WithdrawlInputPort {

    private final TransactionRepositoryOutput transactionRepositoryOutput;

    public WithdrawlFeature(TransactionRepositoryOutput transactionRepositoryOutput) {
        this.transactionRepositoryOutput = transactionRepositoryOutput;
    }

    public void withdraw(int withdrawlAmount) {
        final int currentBalance = calculateAndGetCurrentBalance();

        Transaction transaction = new Transaction(
                TransactionType.WITHDRAWL,
                LocalDate.now(),
                withdrawlAmount * -1,
                currentBalance - withdrawlAmount);

        transactionRepositoryOutput.saveTransaction(transaction);
    }

    private int calculateAndGetCurrentBalance() {
        return transactionRepositoryOutput.findAllTransactions().stream()
                .mapToInt(Transaction::getAmount)
                .sum();
    }
}
