package com.sg.bankaccountkata.core.feature;

import com.sg.bankaccountkata.core.domain.Transaction;
import com.sg.bankaccountkata.core.port.in.PrintStatementInputPort;
import com.sg.bankaccountkata.core.port.out.PrinterOutput;
import com.sg.bankaccountkata.core.port.out.TransactionRepositoryOutput;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

public class PrintStatementFeature implements PrintStatementInputPort {

    private static final String STATEMENT_HEADER = "OPERATION | DATE | AMOUNT | BALANCE";
    private final static DateTimeFormatter DATE_FORMAT =
            new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy").toFormatter();

    private final PrinterOutput printerOutput;
    private final TransactionRepositoryOutput transactionRepositoryOutput;


    public PrintStatementFeature(PrinterOutput printerOutput,
                                 TransactionRepositoryOutput transactionRepositoryOutput) {
        this.printerOutput = printerOutput;
        this.transactionRepositoryOutput = transactionRepositoryOutput;
    }

    public void printStatement() {
        List<Transaction> transactions = this.transactionRepositoryOutput.findAllTransactions();

        StringBuilder statement = new StringBuilder();

        statement.append(STATEMENT_HEADER);

        transactions.stream()
                .forEach(transaction -> {
                    statement.append("\n");
                    statement.append(format(transaction));
                });

        printerOutput.print(statement.toString());
    }

    private String format(Transaction transaction) {
        return String.format("%s" + " | " + "%s" + " | " + "%.2f€" + " | " + "%.2f€",
                transaction.getTransactionType().toString(),
                transaction.getDate().format(DATE_FORMAT),
                (float) transaction.getAmount(),
                (float) transaction.getBalance());
    }
}
