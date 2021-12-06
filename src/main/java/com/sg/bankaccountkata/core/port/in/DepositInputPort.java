package com.sg.bankaccountkata.core.port.in;

import com.sg.bankaccountkata.core.exception.NegativeAmountException;

public interface DepositInputPort {
    void deposit(int depositAmount) throws NegativeAmountException;
}
