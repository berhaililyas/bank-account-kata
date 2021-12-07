package com.sg.bankaccountkata;

import com.sg.bankaccountkata.adapter.statement.StatementFormatterFeature;
import com.sg.bankaccountkata.adapter.statement.StatementPrinterFeature;
import com.sg.bankaccountkata.adapter.transaction.TransactionFormatterFeature;
import com.sg.bankaccountkata.adapter.transaction.TransactionRepositoryFeature;
import com.sg.bankaccountkata.core.exception.NegativeAmountException;
import com.sg.bankaccountkata.core.exception.NotEnoughMoneyException;
import com.sg.bankaccountkata.core.feature.DepositFeature;
import com.sg.bankaccountkata.core.feature.PrintStatementFeature;
import com.sg.bankaccountkata.core.feature.WithdrawlFeature;
import com.sg.bankaccountkata.core.port.in.DepositInputPort;
import com.sg.bankaccountkata.core.port.in.PrintStatementInputPort;
import com.sg.bankaccountkata.core.port.in.WithdrawlInputPort;
import com.sg.bankaccountkata.core.port.out.statement.StatementFormatterOutput;
import com.sg.bankaccountkata.core.port.out.statement.StatementPrinterOutput;
import com.sg.bankaccountkata.core.port.out.transaction.TransactionFormatterOutput;
import com.sg.bankaccountkata.core.port.out.transaction.TransactionRepositoryOutput;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankAccountKataApplication {

    public static void main(String[] args) throws NegativeAmountException, NotEnoughMoneyException {

        TransactionRepositoryOutput transactionRepositoryFeature = new TransactionRepositoryFeature();

        StatementPrinterOutput statementPrinterFeature = new StatementPrinterFeature();
        TransactionFormatterOutput transactionFormatterFeature = new TransactionFormatterFeature();
        StatementFormatterOutput statementFormatterFeature = new StatementFormatterFeature(statementPrinterFeature, transactionFormatterFeature);

        DepositInputPort depositFeature = new DepositFeature(transactionRepositoryFeature);
        WithdrawlInputPort withdrawlFeature = new WithdrawlFeature(transactionRepositoryFeature);
        PrintStatementInputPort printStatementFeature = new PrintStatementFeature(transactionRepositoryFeature, statementFormatterFeature);

        depositFeature.deposit(1000);
        withdrawlFeature.withdraw(300);
        withdrawlFeature.withdraw(50);
        depositFeature.deposit(500);

        printStatementFeature.printStatement();
    }

}
