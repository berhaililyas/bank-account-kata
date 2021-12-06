package com.sg.bankaccountkata.core.feature;

import com.sg.bankaccountkata.core.domain.Transaction;
import com.sg.bankaccountkata.core.domain.TransactionType;
import com.sg.bankaccountkata.core.port.out.TransactionRepositoryOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static java.util.Collections.emptyList;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

public class DepositFeatureTest {

    private DepositFeature depositFeature;

    private TransactionRepositoryOutput transactionRepositoryOutputMock;

    @BeforeEach
    public void setUp() {
        this.transactionRepositoryOutputMock = Mockito.mock(TransactionRepositoryOutput.class);

        this.depositFeature = new DepositFeature(transactionRepositoryOutputMock);
    }

    @Test
    public void shouldMakeADeposit() {
        // Input objects
        int moneyToSave = 100;

        // Expected objects
        Transaction deposit = new Transaction(TransactionType.DEPOSIT, LocalDate.now(), moneyToSave, moneyToSave);

        // Mockito expectations
        given(transactionRepositoryOutputMock.findAllTransactions()).willReturn(emptyList());
        doNothing().when(transactionRepositoryOutputMock).saveTransaction(isA(Transaction.class));

        // Execute the method being tested
        depositFeature.deposit(moneyToSave);

        // Validation
        verify(transactionRepositoryOutputMock).saveTransaction(deposit);
    }
}
