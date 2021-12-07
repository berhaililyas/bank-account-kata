package com.sg.bankaccountkata.adapter.statement;

import com.sg.bankaccountkata.core.domain.Transaction;
import com.sg.bankaccountkata.core.port.out.statement.StatementFormatterOutput;
import com.sg.bankaccountkata.core.port.out.statement.StatementPrinterOutput;
import com.sg.bankaccountkata.core.port.out.transaction.TransactionFormatterOutput;

import java.util.List;

public class StatementFormatterFeature implements StatementFormatterOutput {

    private static final String STATEMENT_HEADER = "OPERATION | DATE | AMOUNT | BALANCE";

    private final StatementPrinterOutput statementPrinterOutput;
    private final TransactionFormatterOutput transactionFormatterOutput;

    public StatementFormatterFeature(StatementPrinterOutput statementPrinterOutput, TransactionFormatterOutput transactionFormatterOutput) {
        this.statementPrinterOutput = statementPrinterOutput;
        this.transactionFormatterOutput = transactionFormatterOutput;
    }

    public void print(List<Transaction> transactions) {
        StringBuilder statement = new StringBuilder();

        statement.append(printHeader())
                .append(printStatementLines(transactions));

        statementPrinterOutput.print(statement.toString());
    }

    private StringBuilder printHeader() {
        StringBuilder header = new StringBuilder();
        return header.append(STATEMENT_HEADER);
    }

    private StringBuilder printStatementLines(List<Transaction> transactions) {
        StringBuilder statementLines = new StringBuilder();
        transactions.stream()
                .forEach(transaction -> {
                    statementLines.append("\n");
                    statementLines.append(transactionFormatterOutput.format(transaction));
                });
        return statementLines;
    }
}
