package com.sg.bankaccountkata.adapter;

import com.sg.bankaccountkata.core.domain.Transaction;
import com.sg.bankaccountkata.core.port.out.StatementFormatterOutput;
import com.sg.bankaccountkata.core.port.out.StatementPrinterOutput;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

public class StatementFormatterFeature implements StatementFormatterOutput {

    private static final String STATEMENT_HEADER = "OPERATION | DATE | AMOUNT | BALANCE";
    private final static DateTimeFormatter DATE_FORMAT =
            new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy").toFormatter();

    private final StatementPrinterOutput statementPrinterOutput;

    public StatementFormatterFeature(StatementPrinterOutput statementPrinterOutput) {
        this.statementPrinterOutput = statementPrinterOutput;
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
                    statementLines.append(format(transaction));
                });
        return statementLines;
    }

    private String format(Transaction transaction) {
        return String.format("%s" + " | " + "%s" + " | " + "%.2f€" + " | " + "%.2f€",
                transaction.getTransactionType().toString(),
                transaction.getDate().format(DATE_FORMAT),
                (float) transaction.getAmount(),
                (float) transaction.getBalance());
    }
}
