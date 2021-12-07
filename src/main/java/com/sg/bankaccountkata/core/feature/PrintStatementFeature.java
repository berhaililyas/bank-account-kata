package com.sg.bankaccountkata.core.feature;

import com.sg.bankaccountkata.core.port.in.PrintStatementInputPort;
import com.sg.bankaccountkata.core.port.out.PrinterOutput;

public class PrintStatementFeature implements PrintStatementInputPort {

    private static final String STATEMENT_HEADER = "OPERATION | DATE | AMOUNT | BALANCE";

    private final PrinterOutput printerOutput;

    public PrintStatementFeature(PrinterOutput printerOutput) {
        this.printerOutput = printerOutput;
    }

    public void printStatement() {
        printerOutput.print(STATEMENT_HEADER);
    }
}
