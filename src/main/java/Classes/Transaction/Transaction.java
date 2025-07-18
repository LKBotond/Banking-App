package Classes.Transaction;

import Classes.accounts.AccountDAO;

import java.sql.SQLDataException;
import java.sql.SQLException;

import Classes.accounts.Account;

public class Transaction {
    private AccountDAO fetcher;
    private TransactionDAO setter;

    Transaction(AccountDAO fetcher, TransactionDAO setter) {
        this.fetcher = fetcher;
        this.setter = setter;
    }

    public boolean transfer(Account sender, Account receiver, int sum) {

        if (!hasSufficientFunds(sender, sum)) {
            return false;
        }
        performTransfer(sender, receiver, sum);
        setter.setAutoCommit(false);
        updateDatabase(sender, receiver);

        logTransaction(sender, receiver, sum);
        setter.setAutoCommit(true);

        return true;
    }

    private boolean hasSufficientFunds(Account sender, int sum) {
        return sender.getBalance() >= sum;
    }

    private void performTransfer(Account sender, Account receiver, int sum) {
        sender.subtractFromFunds(sum);
        receiver.addToFunds(sum);
    }

    private boolean updateDatabase(Account sender, Account receiver) {
        try {
            fetcher.updateBalanceForAccount(sender.getAccounID(), sender.getBalance());
            fetcher.updateBalanceForAccount(receiver.getAccounID(), receiver.getBalance());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void logTransaction(Account sender, Account receiver, int sum) {
        fetcher.recordTransaction(sender.getAccounID(), receiver.getAccounID(), sum);
    }
}
