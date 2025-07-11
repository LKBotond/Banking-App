package Classes.Transaction;

import Classes.accounts.AccountDAO;
import Classes.accounts.Account;

public class Transaction {
    private AccountDAO fetcher;

    Transaction(AccountDAO fetcher) {
        this.fetcher = fetcher;
    }

    public boolean transfer(Account sender, Account receiver, int sum) {

        if (!hasSufficientFunds(sender, sum)) {
            return false;
        }
        performTransfer(sender, receiver, sum);
        updateDatabase(sender, receiver);
        logTransaction(sender, receiver, sum);
        return true;
    }

    private boolean hasSufficientFunds(Account sender, int sum) {
        return sender.getBalance() >= sum;
    }

    private void performTransfer(Account sender, Account receiver, int sum) {
        sender.subtractFromFunds(sum);
        receiver.addToFunds(sum);
    }

    private void updateDatabase(Account sender, Account receiver) {
        fetcher.updateBalanceForAccount(sender.getAccounID(), sender.getBalance());
        fetcher.updateBalanceForAccount(receiver.getAccounID(), receiver.getBalance());
    }

    private void logTransaction(Account sender, Account receiver, int sum) {
        fetcher.recordTransaction(sender.getAccounID(), receiver.getAccounID(), sum);
    }
}
