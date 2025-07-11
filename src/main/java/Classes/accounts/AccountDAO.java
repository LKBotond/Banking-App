package Classes.accounts;

import java.util.ArrayList;

import Classes.DBInterface.DBInterface;
import Classes.DBInterface.DBQueries;
import Classes.Helpers.Utils;

public class AccountDAO {
    DBInterface connection;

    public AccountDAO(DBInterface connection) {
        this.connection = connection;
    }

    public ArrayList<Integer> getAccountsByUserID(int user_ID) {
        return connection.getIntArrayList(DBQueries.GET_ACCOUNT_FOR_USER, Utils.objectListify(user_ID));
    }

    public int getBalanceForAccount(int account_ID) {
        return connection.getInt(DBQueries.GET_BALANCE, Utils.objectListify(account_ID));
    }

    public void updateBalanceForAccount(int account_ID, int funds) {
        ArrayList<Object> balance_And_Key = new ArrayList<>();
        balance_And_Key.add(funds);
        balance_And_Key.add(account_ID);

        connection.insertData(DBQueries.UPDATE_BALANCE, balance_And_Key);
    }

    public void addAccountToPerson(int user_ID, int funds) {
        ArrayList<Object> key_And_Balance = new ArrayList<>();
        key_And_Balance.add(user_ID);
        key_And_Balance.add(funds);

        connection.insertData(DBQueries.ADD_ACCOUNT, key_And_Balance);
    }

    public void recordTransaction(int sender_ID, int receiver_ID, int sum) {
        ArrayList<Object> sender_Receiver_And_Sum = new ArrayList<>();
        sender_Receiver_And_Sum.add(sender_ID);
        sender_Receiver_And_Sum.add(receiver_ID);
        sender_Receiver_And_Sum.add(sum);
        connection.insertData(DBQueries.UPDATE_MASTER_RECORD, sender_Receiver_And_Sum);
    }

}
