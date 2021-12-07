package com.sg.bankaccountkata.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatementPrinterFeatureTest {

    private StatementPrinterFeature statementPrinterFeature;

    @BeforeEach
    public void setUp() {
        this.statementPrinterFeature = new StatementPrinterFeature();
    }

    @Test
    public void shoudPrintSomeText() {
        // Input objects
        String text = "Print the input in the console";

        // Execute the method being tested
        statementPrinterFeature.print(text);

        // Validation
        assertEquals("Print the input in the console", text);
    }
}
