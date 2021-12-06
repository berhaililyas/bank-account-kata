package com.sg.bankaccountkata.adapter;

import com.sg.bankaccountkata.core.domain.Transaction;
import com.sg.bankaccountkata.core.domain.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
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

}
