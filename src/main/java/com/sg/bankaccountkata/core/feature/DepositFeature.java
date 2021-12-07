package com.sg.bankaccountkata.core.feature;

import com.sg.bankaccountkata.core.domain.Transaction;
import com.sg.bankaccountkata.core.domain.TransactionType;
import com.sg.bankaccountkata.core.exception.NegativeAmountException;
import com.sg.bankaccountkata.core.port.in.DepositInputPort;
import com.sg.bankaccountkata.core.port.out.transaction.TransactionRepositoryOutput;

import java.time.LocalDate;

public class DepositFeature implements DepositInputPort {

    private final TransactionRepositoryOutput transactionRepositoryOutput;

    public DepositFeature(TransactionRepositoryOutput transactionRepositoryFeature) {
        this.transactionRepositoryOutput = transactionRepositoryFeature;
    }

    public void deposit(int depositAmount) throws NegativeAmountException {
        if (depositAmount < 0) {
            throw new NegativeAmountException("Impossible to make a negative transaction");
        }

        final int currentBalance = calculateAndGetCurrentBalance();
        Transaction transaction = new Transaction(
                TransactionType.DEPOSIT,
                LocalDate.now(),
                depositAmount,
                currentBalance + depositAmount);

        transactionRepositoryOutput.saveTransaction(transaction);
    }

    private int calculateAndGetCurrentBalance() {
        return transactionRepositoryOutput.findAllTransactions().stream()
                .mapToInt(Transaction::getAmount)
                .sum();
    }
}
