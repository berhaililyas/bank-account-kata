package com.sg.bankaccountkata.adapter;


import com.sg.bankaccountkata.core.port.out.StatementPrinterOutput;

public class StatementPrinterFeature implements StatementPrinterOutput {

    public void print(String text) {
        System.out.println(text);
    }
}
