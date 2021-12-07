package com.sg.bankaccountkata.adapter.transaction;

import com.sg.bankaccountkata.core.domain.Transaction;
import com.sg.bankaccountkata.core.domain.TransactionType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TransactionFormatterFeatureTest {

    private TransactionFormatterFeature transactionFormatterFeature = new TransactionFormatterFeature();

    @Test
    void shoudFormatADepositTransaction() {
        // Input objects
        Transaction deposit = new Transaction(TransactionType.DEPOSIT, LocalDate.of(2021, 11, 28), 1000, 1000);

        // Execute the method being tested
        String result = transactionFormatterFeature.format(deposit);

        // Validation
        assertThat(result, equalTo("DEPOSIT | 28/11/2021 | 1000,00€ | 1000,00€"));
    }

    @Test
    void shouldFormatAWithdrawalTransaction() {
        // Input objects
        Transaction withdrawl = new Transaction(TransactionType.WITHDRAWL, LocalDate.of(2021, 11, 30), -100, -100);

        // Execute the method being tested
        String result = transactionFormatterFeature.format(withdrawl);

        // Validation
        assertThat(result, equalTo("WITHDRAWL | 30/11/2021 | -100,00€ | -100,00€"));
    }
}
