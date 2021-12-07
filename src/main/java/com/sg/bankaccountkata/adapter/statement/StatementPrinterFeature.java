package com.sg.bankaccountkata.adapter.statement;


import com.sg.bankaccountkata.core.port.out.statement.StatementPrinterOutput;

public class StatementPrinterFeature implements StatementPrinterOutput {

    public void print(String text) {
        System.out.println(text);
    }
}
