package com.sg.bankaccountkata.adapter;

import com.sg.bankaccountkata.core.domain.Transaction;
import com.sg.bankaccountkata.core.port.out.TransactionFormatterOutput;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class TransactionFormatterFeature implements TransactionFormatterOutput {
    private final static String SEPARATOR = " | ";
    private final static DateTimeFormatter DATE_FORMAT =
            new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy").toFormatter();

    public String format(Transaction transaction) {
        return String.format("%s" + SEPARATOR + "%s" + SEPARATOR + "%.2f€" + SEPARATOR + "%.2f€",
                transaction.getTransactionType().toString(),
                transaction.getDate().format(DATE_FORMAT),
                (float) transaction.getAmount(),
                (float) transaction.getBalance());
    }
}
