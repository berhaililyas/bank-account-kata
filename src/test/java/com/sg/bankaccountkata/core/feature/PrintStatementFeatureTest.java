package com.sg.bankaccountkata.core.feature;

import com.sg.bankaccountkata.core.port.out.PrinterOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

public class PrintStatementFeatureTest {

    private PrintStatementFeature printStatementFeature;

    private PrinterOutput printerOutputMock;

    @BeforeEach
    void setUp() {
        this.printerOutputMock = Mockito.mock(PrinterOutput.class);

        this.printStatementFeature = new PrintStatementFeature(printerOutputMock);
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
        fail("Not yet implemented");
    }

    @Test
    void shouldPrintStatementWithMultipleTransactions() {
        fail("Not yet implemented");
    }
}
