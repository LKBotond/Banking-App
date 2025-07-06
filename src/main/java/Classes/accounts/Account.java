package Classes.accounts;

import Classes.Helpers.Utils;
import Classes.DBInterface.*;
import java.util.ArrayList;

public class Account {
    private int account_ID;
    private int funds;

    public Account(DBInterface connection, int account_ID_Param) {
        account_ID = account_ID_Param;
        funds = connection.getInt(DBQueries.GET_BALANCE, Utils.objectListify(account_ID));

    }

    public static ArrayList<Integer> getAllAccountsForUSer(DBInterface connection, int user_ID) {
        ArrayList<Integer> data_List = connection.getIntArrayLIst
        (DBQueries.GET_ACCOUNT_FOR_USER, Utils.objectListify(user_ID));
        return data_List;
    }

    public int getBalance() {
        return funds;
    }

    public int getAccounID() {
        return account_ID;
    }

    public void addToFunds(int amounth) {
        funds += amounth;
    }

    public boolean subtractFromFunds(int amount) {
        if (funds >= amount) {
            funds -= amount;
            return true;
        }
        return false;
    }

}
