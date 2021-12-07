package com.sg.bankaccountkata.core.port.out;

import com.sg.bankaccountkata.core.domain.Transaction;

public interface TransactionFormatterOutput {

    String format(Transaction transaction);
}
