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
        performTransfer(sender, receiver, sum);// updates in memory objects
        setter.setAutoCommit(false);
        boolean update = updateDatabase(sender, receiver);
        boolean log_Succesfull = logTransaction(sender, receiver, sum);
        if (update && log_Succesfull) {
            if (setter.commitChanges()) {
                setter.setAutoCommit(true);
                return true;
            }
        }
        setter.rollback();
        setter.setAutoCommit(true);
        return false;
    }

    private boolean hasSufficientFunds(Account sender, int sum) {
        return sender.getBalance() >= sum;
    }

    private void performTransfer(Account sender, Account receiver, int sum) {
        sender.subtractFromFunds(sum);
        receiver.addToFunds(sum);
    }

    private boolean updateDatabase(Account sender, Account receiver) {
        boolean update_1 = fetcher.updateBalanceForAccount(sender.getAccounID(), sender.getBalance());
        boolean update_2 = fetcher.updateBalanceForAccount(receiver.getAccounID(), receiver.getBalance());
        if (update_1 && update_2) {
            return true;
        }
        return false;
    }

    private boolean logTransaction(Account sender, Account receiver, int sum) {
        boolean log_Succesfull = fetcher.recordTransaction(sender.getAccounID(), receiver.getAccounID(), sum);
        if (log_Succesfull) {
            return true;
        }
        return false;
    }
}
