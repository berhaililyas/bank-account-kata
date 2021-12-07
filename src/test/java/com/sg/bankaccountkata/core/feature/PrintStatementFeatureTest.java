package com.sg.bankaccountkata.core.feature;

import com.sg.bankaccountkata.core.domain.Transaction;
import com.sg.bankaccountkata.core.domain.TransactionType;
import com.sg.bankaccountkata.core.port.out.statement.StatementFormatterOutput;
import com.sg.bankaccountkata.core.port.out.transaction.TransactionRepositoryOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class PrintStatementFeatureTest {

    private PrintStatementFeature printStatementFeature;

    private TransactionRepositoryOutput transactionRepositoryOutputMock;
    private StatementFormatterOutput statementFormatterOutputMock;

    @BeforeEach
    void setUp() {
        this.transactionRepositoryOutputMock = Mockito.mock(TransactionRepositoryOutput.class);
        this.statementFormatterOutputMock = Mockito.mock(StatementFormatterOutput.class);

        this.printStatementFeature = new PrintStatementFeature(transactionRepositoryOutputMock, statementFormatterOutputMock);
    }

    @Test
    void shouldPrintStatementWithEmptyTransactions() {
        // Input objects
        List<Transaction> transactions = EMPTY_LIST;

        // Mockito expectations
        given(transactionRepositoryOutputMock.findAllTransactions()).willReturn(transactions);

        // Execute the method being tested
        printStatementFeature.printStatement();

        // Validation
        verify(statementFormatterOutputMock).print(transactions);
    }

    @Test
    void shouldPrintStatementWithOneTransaction() {
        // Input objects
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, LocalDate.of(2021, 11, 28), 1000, 1000);

        // Expected objects
        List<Transaction> transactions = asList(transaction);

        // Mockito expectations
        given(transactionRepositoryOutputMock.findAllTransactions()).willReturn(transactions);

        // Execute the method being tested
        printStatementFeature.printStatement();

        // Validation
        verify(statementFormatterOutputMock).print(transactions);
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
        verify(statementFormatterOutputMock).print(transactions);
    }
}
