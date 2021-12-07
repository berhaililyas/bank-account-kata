package com.sg.bankaccountkata.adapter;

import com.sg.bankaccountkata.core.domain.Transaction;
import com.sg.bankaccountkata.core.domain.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TransactionRepositoryFeatureTest {

    private TransactionRepositoryFeature transactionRepositoryFeature;

    @BeforeEach
    public void setUp() {
        this.transactionRepositoryFeature = new TransactionRepositoryFeature();
    }

    @Test
    public void shouldSaveATransaction() {
        // Input objects
        int moneyToSave = 100;
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, LocalDate.now(), moneyToSave, moneyToSave);

        // Expected objects
        List<Transaction> transactions = asList(transaction);

        // Execute the method being tested
        transactionRepositoryFeature.saveTransaction(transaction);

        // Validation
        assertThat(transactions.size(), is(1));
        assertThat(transactions.get(0), is(transaction));
    }

    @Test
    public void shouldBeInitiallyEmpty() {
        // Execute the method being tested
        List<Transaction> transactions = transactionRepositoryFeature.findAllTransactions();

        // Validation
        assertThat(transactions.size(), is(0));
    }

    @Test
    public void shouldReturnAllSavedTransactions() {
        // Input objects
        Transaction transaction_1 = new Transaction(TransactionType.DEPOSIT, LocalDate.of(2021, 11, 28), 1000, 1000);
        Transaction transaction_2 = new Transaction(TransactionType.WITHDRAWL, LocalDate.of(2021, 11, 30), -100, 900);
        Transaction transaction_3 = new Transaction(TransactionType.DEPOSIT, LocalDate.of(2021, 12, 05), 500, 1400);

        // Expected objects
        List<Transaction> transactions = transactionRepositoryFeature.findAllTransactions();

        // Execute the method being tested
        transactionRepositoryFeature.saveTransaction(transaction_1);
        transactionRepositoryFeature.saveTransaction(transaction_2);
        transactionRepositoryFeature.saveTransaction(transaction_3);

        // Validation
        assertThat(transactions.size(), is(3));
        assertThat(transactions.get(0), equalTo(transaction_1));
        assertThat(transactions.get(1), equalTo(transaction_2));
        assertThat(transactions.get(2), equalTo(transaction_3));
    }

}
