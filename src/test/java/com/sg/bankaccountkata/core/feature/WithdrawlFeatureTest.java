package com.sg.bankaccountkata.core.feature;

import com.sg.bankaccountkata.core.domain.Transaction;
import com.sg.bankaccountkata.core.domain.TransactionType;
import com.sg.bankaccountkata.core.port.out.TransactionRepositoryOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class WithdrawlFeatureTest {

    private WithdrawlFeature withdrawlFeature;

    private TransactionRepositoryOutput transactionRepositoryOutputMock;

    @BeforeEach
    public void setUp() {
        this.transactionRepositoryOutputMock = Mockito.mock(TransactionRepositoryOutput.class);

        this.withdrawlFeature = new WithdrawlFeature(this.transactionRepositoryOutputMock);
    }

    @Test
    public void shouldMakeAWithdrawal() {
        // Input objects
        int moneyToRetrive = 100;
        List<Transaction> transactions = asList(new Transaction(TransactionType.DEPOSIT, LocalDate.now(), 100, 100));

        // Expected objects
        Transaction withdrawl = new Transaction(TransactionType.WITHDRAWL, LocalDate.now(), -moneyToRetrive, -moneyToRetrive);

        // Mockito expectations
        given(transactionRepositoryOutputMock.findAllTransactions()).willReturn(transactions);
        doNothing().when(transactionRepositoryOutputMock).saveTransaction(isA(Transaction.class));

        // Execute the method being tested
        withdrawlFeature.withdraw(moneyToRetrive);

        // Validation
        verify(transactionRepositoryOutputMock, times(1)).saveTransaction(withdrawl);
    }

}
