package com.sg.bankaccountkata.core.feature;

import com.sg.bankaccountkata.core.domain.Transaction;
import com.sg.bankaccountkata.core.domain.TransactionType;
import com.sg.bankaccountkata.core.exception.NegativeAmountException;
import com.sg.bankaccountkata.core.port.out.transaction.TransactionRepositoryOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class DepositFeatureTest {

    private DepositFeature depositFeature;

    private TransactionRepositoryOutput transactionRepositoryOutputMock;

    @BeforeEach
    void setUp() {
        this.transactionRepositoryOutputMock = Mockito.mock(TransactionRepositoryOutput.class);

        this.depositFeature = new DepositFeature(transactionRepositoryOutputMock);
    }

    @Test
    void shouldMakeADeposit() throws NegativeAmountException {
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
        verify(transactionRepositoryOutputMock, times(1)).saveTransaction(deposit);
    }

    @Test
    void shouldFailDepositWhenNegativeAmount() {
        // Input objects
        int moneyToSave = -100;

        // Expected objects
        String expectedMessage = "Impossible to make a negative transaction";

        // Execute the method being tested
        Exception exception = assertThrows(NegativeAmountException.class, () -> {
            depositFeature.deposit(moneyToSave);
        });
        String actualMessage = exception.getMessage();

        // Validation
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
