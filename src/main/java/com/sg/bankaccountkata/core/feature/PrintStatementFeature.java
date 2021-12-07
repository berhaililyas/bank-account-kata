package com.sg.bankaccountkata.core.feature;

import com.sg.bankaccountkata.core.port.in.PrintStatementInputPort;
import com.sg.bankaccountkata.core.port.out.StatementFormatterOutput;
import com.sg.bankaccountkata.core.port.out.TransactionRepositoryOutput;

public class PrintStatementFeature implements PrintStatementInputPort {

    private final TransactionRepositoryOutput transactionRepositoryOutput;
    private final StatementFormatterOutput statementFormatterFeature;

    public PrintStatementFeature(TransactionRepositoryOutput transactionRepositoryOutput,
                                 StatementFormatterOutput statementFormatterFeature) {
        this.transactionRepositoryOutput = transactionRepositoryOutput;
        this.statementFormatterFeature = statementFormatterFeature;
    }

    public void printStatement() {
        this.statementFormatterFeature.print(this.transactionRepositoryOutput.findAllTransactions());
    }
}
