package com.sg.bankaccountkata.adapter;

import com.sg.bankaccountkata.core.domain.Transaction;
import com.sg.bankaccountkata.core.domain.TransactionType;
import com.sg.bankaccountkata.core.port.out.StatementPrinterOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

public class StatementFormatterFeatureTest {

    private StatementFormatterFeature statementFormatterFeature;

    private StatementPrinterOutput statementPrinterOutputMock;

    @BeforeEach
    void setUp() {
        this.statementPrinterOutputMock = Mockito.mock(StatementPrinterOutput.class);

        this.statementFormatterFeature = new StatementFormatterFeature(statementPrinterOutputMock);
    }

    @Test
    void shouldPrintEmptyStatement() {
        // Input objects
        List<Transaction> NO_TRANSACTIONS = EMPTY_LIST;

        // Mockito expectations
        doNothing().when(statementPrinterOutputMock).print(isA(String.class));

        // Execute the method being tested
        statementFormatterFeature.print(NO_TRANSACTIONS);

        // Validation
        verify(statementPrinterOutputMock).print("OPERATION | DATE | AMOUNT | BALANCE");
    }

    @Test
    void shouldPrintStatementWithOneTransaction() {
        // Input objects
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, LocalDate.of(2021, 11, 28), 1000, 1000);

        // Expected objects
        List<Transaction> transactions = Arrays.asList(transaction);

        // Execute the method being tested
        statementFormatterFeature.print(transactions);

        // Validation
        verify(statementPrinterOutputMock).print(
                "OPERATION | DATE | AMOUNT | BALANCE\n" +
                        "DEPOSIT | 28/11/2021 | 1000,00€ | 1000,00€");
    }

    @Test
    void shouldPrintStatementWithMultipleTransactions() {
        // Input objects
        Transaction deposit_1 = new Transaction(TransactionType.DEPOSIT, LocalDate.of(2021, 11, 28), 1000, 1000);
        Transaction withdrawl = new Transaction(TransactionType.WITHDRAWL, LocalDate.of(2021, 11, 30), -100, 900);
        Transaction deposit_2 = new Transaction(TransactionType.DEPOSIT, LocalDate.of(2021, 12, 05), 500, 1400);

        // Expected objects
        List<Transaction> transactions = Arrays.asList(deposit_1, withdrawl, deposit_2);

        // Execute the method being tested
        statementFormatterFeature.print(transactions);

        // Validation
        verify(statementPrinterOutputMock).print(
                "OPERATION | DATE | AMOUNT | BALANCE\n" +
                        "DEPOSIT | 28/11/2021 | 1000,00€ | 1000,00€\n" +
                        "WITHDRAWL | 30/11/2021 | -100,00€ | 900,00€\n" +
                        "DEPOSIT | 05/12/2021 | 500,00€ | 1400,00€");
    }
}
