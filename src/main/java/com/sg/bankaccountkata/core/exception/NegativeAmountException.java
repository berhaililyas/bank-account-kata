package com.sg.bankaccountkata.core.exception;

public class NegativeAmountException extends Exception {
    public NegativeAmountException(String message){
            super(message);
    }
}
