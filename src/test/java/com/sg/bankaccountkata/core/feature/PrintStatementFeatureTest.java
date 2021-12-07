package com.sg.bankaccountkata.core.feature;

import com.sg.bankaccountkata.core.domain.Transaction;
import com.sg.bankaccountkata.core.domain.TransactionType;
import com.sg.bankaccountkata.core.port.out.PrinterOutput;
import com.sg.bankaccountkata.core.port.out.TransactionRepositoryOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

public class PrintStatementFeatureTest {

    private PrintStatementFeature printStatementFeature;

    private PrinterOutput printerOutputMock;
    private TransactionRepositoryOutput transactionRepositoryOutputMock;

    @BeforeEach
    void setUp() {
        this.printerOutputMock = Mockito.mock(PrinterOutput.class);
        this.transactionRepositoryOutputMock = Mockito.mock(TransactionRepositoryOutput.class);

        this.printStatementFeature = new PrintStatementFeature(printerOutputMock, transactionRepositoryOutputMock);
    }

    @Test
    void shouldPrintEmptyStatement() {
        // Mockito expectations
        doNothing().when(printerOutputMock).print(isA(String.class));

        // Execute the method being tested
        printStatementFeature.printStatement();

        // Validation
        verify(printerOutputMock).print("OPERATION | DATE | AMOUNT | BALANCE");
    }

    @Test
    void shouldPrintStatementWithOneTransaction() {
        // Input objects
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, LocalDate.of(2021, 11, 28), 1000, 1000);

        // Expected objects
        List<Transaction> transactions = Arrays.asList(transaction);

        // Mockito expectations
        given(transactionRepositoryOutputMock.findAllTransactions()).willReturn(transactions);

        // Execute the method being tested
        printStatementFeature.printStatement();

        // Validation
        verify(printerOutputMock).print(
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

        // Mockito expectations
        given(transactionRepositoryOutputMock.findAllTransactions()).willReturn(transactions);

        // Execute the method being tested
        printStatementFeature.printStatement();

        // Validation
        verify(printerOutputMock).print(
                "OPERATION | DATE | AMOUNT | BALANCE\n" +
                        "DEPOSIT | 28/11/2021 | 1000,00€ | 1000,00€\n" +
                        "WITHDRAWL | 30/11/2021 | -100,00€ | 900,00€\n" +
                        "DEPOSIT | 05/12/2021 | 500,00€ | 1400,00€");
    }
}
