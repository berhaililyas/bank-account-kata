package com.sg.bankaccountkata.adapter.statement;

import com.sg.bankaccountkata.core.domain.Transaction;
import com.sg.bankaccountkata.core.domain.TransactionType;
import com.sg.bankaccountkata.core.port.out.statement.StatementPrinterOutput;
import com.sg.bankaccountkata.core.port.out.transaction.TransactionFormatterOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

public class StatementFormatterFeatureTest {

    private StatementFormatterFeature statementFormatterFeature;

    private StatementPrinterOutput statementPrinterOutputMock;
    private TransactionFormatterOutput transactionFormatterOutputMock;

    @BeforeEach
    void setUp() {
        this.statementPrinterOutputMock = Mockito.mock(StatementPrinterOutput.class);
        this.transactionFormatterOutputMock = Mockito.mock(TransactionFormatterOutput.class);

        this.statementFormatterFeature = new StatementFormatterFeature(statementPrinterOutputMock, transactionFormatterOutputMock);
    }

    @Test
    void shouldFormatAEmptyStatement() {
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
    void shouldFormatAStatementWithOneTransaction() {
        // Input objects
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, LocalDate.of(2021, 11, 28), 1000, 1000);

        // Expected objects
        List<Transaction> transactions = Arrays.asList(transaction);

        // Mockito expectations
        when(transactionFormatterOutputMock.format(transaction)).thenReturn("DEPOSIT | 28/11/2021 | 1000,00€ | 1000,00€");

        // Execute the method being tested
        statementFormatterFeature.print(transactions);

        // Validation
        verify(statementPrinterOutputMock).print(
                "OPERATION | DATE | AMOUNT | BALANCE\n" +
                        "DEPOSIT | 28/11/2021 | 1000,00€ | 1000,00€");
    }

    @Test
    void shouldFormatAStatementWithMultipleTransactionsInReverseDateOrder() {
        // Input objects
        Transaction deposit_1 = new Transaction(TransactionType.DEPOSIT, LocalDate.of(2021, 11, 28), 1000, 1000);
        Transaction withdrawl = new Transaction(TransactionType.WITHDRAWL, LocalDate.of(2021, 11, 30), -100, 900);
        Transaction deposit_2 = new Transaction(TransactionType.DEPOSIT, LocalDate.of(2021, 12, 05), 500, 1400);

        // Expected objects
        List<Transaction> transactions = Arrays.asList(deposit_1, withdrawl, deposit_2);

        // Mockito expectations
        when(transactionFormatterOutputMock.format(deposit_1)).thenReturn("DEPOSIT | 28/11/2021 | 1000,00€ | 1000,00€");
        when(transactionFormatterOutputMock.format(withdrawl)).thenReturn("WITHDRAWL | 30/11/2021 | -100,00€ | 900,00€");
        when(transactionFormatterOutputMock.format(deposit_2)).thenReturn("DEPOSIT | 05/12/2021 | 500,00€ | 1400,00€");

        // Execute the method being tested
        statementFormatterFeature.print(transactions);

        // Validation
        verify(statementPrinterOutputMock).print(
                "OPERATION | DATE | AMOUNT | BALANCE\n" +
                        "DEPOSIT | 05/12/2021 | 500,00€ | 1400,00€\n" +
                        "WITHDRAWL | 30/11/2021 | -100,00€ | 900,00€\n" +
                        "DEPOSIT | 28/11/2021 | 1000,00€ | 1000,00€");
    }
}
