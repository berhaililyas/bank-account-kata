package com.sg.bankaccountkata.core.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.time.LocalDate;


public class Transaction {

    private TransactionType transactionType;
    private LocalDate date;
    private int amount;
    private int balance;

    public Transaction(TransactionType transactionType, LocalDate date, int amount, int balance) {
        this.transactionType = transactionType;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public int getAmount() {
        return amount;
    }

    public int getBalance() {
        return balance;
    }

    public LocalDate getDate() {
        return date;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Transaction)) {
            return false;
        }
        Transaction otherTransaction = (Transaction) o;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(getDate(), otherTransaction.getDate());
        builder.append(getAmount(), otherTransaction.getAmount());
        return builder.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(getDate());
        builder.append(getAmount());
        return builder.toHashCode();
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionType='" + transactionType +
                ", date='" + date +
                ", amount=" + amount +
                ", balance=" + balance +
                '}';
    }

}

