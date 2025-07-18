package Classes.accounts;

import java.sql.SQLException;
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
        try {
            return connection.getIntArrayList(DBQueries.GET_ACCOUNT_FOR_USER, Utils.objectListify(user_ID));
        } catch (SQLException e) {
            System.out.println("Failed to get accounts for user: " + e);
            return new ArrayList<>();
        }
    }

    public int getBalanceForAccount(int account_ID) {
        try {
            return connection.getInt(DBQueries.GET_BALANCE, Utils.objectListify(account_ID));
        } catch (SQLException e) {
            System.out.println("Failed to get balance for account: " + e);
            return 0;
        }
    }

    public void updateBalanceForAccount(int account_ID, int funds) {
        ArrayList<Object> balance_And_Key = new ArrayList<>();
        balance_And_Key.add(funds);
        balance_And_Key.add(account_ID);
        try {
            connection.insertData(DBQueries.UPDATE_BALANCE, balance_And_Key);
        } catch (SQLException e) {
            System.out.println("Failed to update balance for account: " + e);
        }
    }

    public void addAccountToPerson(int user_ID, int funds) {
        ArrayList<Object> key_And_Balance = new ArrayList<>();
        key_And_Balance.add(user_ID);
        key_And_Balance.add(funds);

        try {
            connection.insertData(DBQueries.ADD_ACCOUNT, key_And_Balance);
        } catch (SQLException e) {
            System.out.println("Failed to add account to user: " + e);
            ;
        }
    }

    public void recordTransaction(int sender_ID, int receiver_ID, int sum) {
        ArrayList<Object> sender_Receiver_And_Sum = new ArrayList<>();
        sender_Receiver_And_Sum.add(sender_ID);
        sender_Receiver_And_Sum.add(receiver_ID);
        sender_Receiver_And_Sum.add(sum);
        try {
            connection.insertData(DBQueries.UPDATE_MASTER_RECORD, sender_Receiver_And_Sum);
        } catch (SQLException e) {
            System.out.println("Failed to update transaction History: " + e);
        }
    }

}
