package com.sg.bankaccountkata.core.port.out.statement;

import com.sg.bankaccountkata.core.domain.Transaction;

import java.util.List;

public interface StatementFormatterOutput {

    void print(List<Transaction> transactions);
}
