package com.sg.bankaccountkata.core.port.in;

import com.sg.bankaccountkata.core.exception.NegativeAmountException;
import com.sg.bankaccountkata.core.exception.NotEnoughMoneyException;

public interface WithdrawlInputPort {
    void withdraw(int withdrawlAmount) throws NegativeAmountException, NotEnoughMoneyException;
}
