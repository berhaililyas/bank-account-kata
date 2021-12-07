package com.sg.bankaccountkata.core.feature;

import com.sg.bankaccountkata.core.domain.Transaction;
import com.sg.bankaccountkata.core.domain.TransactionType;
import com.sg.bankaccountkata.core.exception.NegativeAmountException;
import com.sg.bankaccountkata.core.exception.NotEnoughMoneyException;
import com.sg.bankaccountkata.core.port.in.WithdrawlInputPort;
import com.sg.bankaccountkata.core.port.out.transaction.TransactionRepositoryOutput;

import java.time.LocalDate;

public class WithdrawlFeature implements WithdrawlInputPort {

    private final TransactionRepositoryOutput transactionRepositoryOutput;

    public WithdrawlFeature(TransactionRepositoryOutput transactionRepositoryOutput) {
        this.transactionRepositoryOutput = transactionRepositoryOutput;
    }

    public void withdraw(int withdrawlAmount) throws NegativeAmountException, NotEnoughMoneyException {
        if (withdrawlAmount < 0) {
            throw new NegativeAmountException("Impossible to make a negative transaction");
        }

        final int currentBalance = calculateAndGetCurrentBalance();
        if (withdrawlAmount > currentBalance) {
            throw new NotEnoughMoneyException("Withdrawal not possible, your account should be recharged");
        }

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
