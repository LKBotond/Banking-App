package Classes.accounts;


public class Account {
    private int account_ID;
    private int funds;

    public Account(int account_ID_Param, int funds) {
        this.account_ID = account_ID_Param;
        this.funds = funds;
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
