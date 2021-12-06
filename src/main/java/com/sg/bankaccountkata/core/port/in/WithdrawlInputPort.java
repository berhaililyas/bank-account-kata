package com.sg.bankaccountkata.core.port.in;

import com.sg.bankaccountkata.core.exception.NegativeAmountException;

public interface WithdrawlInputPort {
    void withdraw(int withdrawlAmount) throws NegativeAmountException;
}
