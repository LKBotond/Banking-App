package Classes.DBInterface;

import java.util.ArrayList;

public final class DBQueries {
    private DBQueries() {
        // Prevent instantiation
    }

    // Tables:
    public static final String logins = "logins";
    public static final String users = "users";
    public static final String accounts = "accounts";
    public static final String masterRecord = "masterRecord";

    public static final ArrayList<String> getTableNames() {
        ArrayList<String> tables = new ArrayList<>();
        tables.add(logins);
        tables.add(users);
        tables.add(accounts);
        tables.add(masterRecord);
        return tables;
    }

    // TABLE CREATION
    public static final String CREATE_LOGIN_TABLE = "CREATE TABLE logins (user_ID INTEGER, login_Time TEXT, logout_Time TEXT, FOREIGN KEY (user_ID) REFERENCES users (user_ID));";
    public static final String CREATE_USER_TABLE = "CREATE TABLE users (user_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, email TEXT, name_Encrypted TEXT, iv TEXT, pass_Hash TEXT, registered TEXT);";
    public static final String CREATE_ACCOUNTS_TABLE = "CREATE TABLE accounts (account_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, user_ID INTEGER NOT NULL, funds INTEGER, FOREIGN KEY (user_ID) REFERENCES users (user_ID));";
    public static final String CREATE_MASTER_RECORD_TABLE = "CREATE TABLE masterRecord (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, sender_ID INTEGER, receiver_ID INTEGER, sum INTEGER, date TEXT, FOREIGN KEY (sender_ID) REFERENCES accounts (account_ID), FOREIGN KEY (receiver_ID) REFERENCES accounts (account_ID));";

    public static final ArrayList<String> tableCreatorStrings() {
        ArrayList<String> creator_Queries = new ArrayList<>();
        creator_Queries.add(CREATE_LOGIN_TABLE);
        creator_Queries.add(CREATE_USER_TABLE);
        creator_Queries.add(CREATE_ACCOUNTS_TABLE);
        creator_Queries.add(CREATE_MASTER_RECORD_TABLE);
        return creator_Queries;
    }
    // DATA QUERYS

    /**
     * Data Query returns:
     * table name (String) from
     * sqlite_master
     * takes input: table_Name (String)
     */
    public static final String QUERY_FOR_TABLE_NAME = "SELECT name FROM sqlite_master WHERE type='table' AND name=?";

    /**
     * Data Quuery returns:
     * pass_Hash(String)
     * Takes one input:
     * email(String)
     */
    public static final String GET_PASS_FOR_AUTHENTICATION = "SELECT pass_Hash FROM users WHERE email=?";

    /**
     * Data Quuery returns:
     * email(String)
     * Takes one input:
     * email(String)
     */
    public static final String GET_EMAIL_FOR_AUTHENTICATION = "SELECT email FROM users WHERE email=?;";

    /**
     * Data Query returns:
     * The userID (int).
     * encrypted name (String)
     * and the IV (String)
     * 
     * Takes one input:
     * one input email(String)
     */
    public static final String GET_USER = "SELECT user_ID, name_Encrypted, iv FROM users WHERE email=?;";

    /**
     * Data Query returns the account_ID(s) (int).
     * Takes one input:
     * userID(String)
     */
    public static final String GET_ACCOUNT_FOR_USER = "SELECT account_ID FROM accounts WHERE (user_ID = ?);";

    /**
     * Data Query returns everything from masterRecord:
     * (Strings and Integers) for each account of the user
     * takes as input user_ID(int)
     */
    public static final String GET_ALL_ACCOUNT_RECORDS_FOR_USER = "SELECT * FROM masterRecord WHERE sender_ID IN (SELECT account_ID FROM accounts WHERE user_ID = ?);";

    /**
     * Data Query returns the current funds (int) for the account
     * takes as input account_ID(int)
     */
    public static final String GET_BALANCE = "SELECT funds FROM accounts WHERE (account_ID = ?);";

    /**
     * Returns each entry from masterRecord:
     * (Strings and Integers) where the account sent funds
     * takes as input account_ID(int)
     */
    public static final String GET_ACCOUNT_RECORDS_SEND = "SELECT * FROM masterRecord WHERE (sender_ID = ?);";

    /**
     * Returns each entry from masterRecord:
     * (Strings and Integers) where the account received funds
     * takes as input account_ID(int)
     */
    public static final String GET_ACCOUNT_RECORDS_RECEIVER = "SELECT * FROM masterRecord WHERE (receiver_ID = ?);";

    /**
     * * Returns each entry from masterRecord:
     * (Strings and Integers) where the two accounts transacted:
     * takes as input two account_ID's
     * sender_ID(int), receiver_ID(int)
     */
    public static final String GET_RECORDS_BETWEEN_TWO_ACCOUNTS = "SELECT * FROM masterRecord WHERE (sender_ID = ? AND receiver_ID = ?);";

    // DATA INSERTION

    /**
     * Inserts a new user into the users table.
     * Parameters: name_Hash (String), pass_Hash (String)
     */
    public static final String ADD_USER = "INSERT INTO users (email, name_Encrypted, iv, pass_Hash, registered) VALUES (?, ?, ?, ?, DATETIME ('now'));";

    /**
     * Inserts a new account into the accounts table.
     * Parameters: user_ID (int), funds (int)
     */
    public static final String ADD_ACCOUNT = "INSERT INTO accounts (user_ID, funds) VALUES (?, ?);";

    /**
     * Inserts Login date into the logins table.
     * Parameters: user_ID (int)
     */
    public static final String ADD_LOGIN_DATE = "INSERT INTO logins (user_ID, login_Time) VALUES (?, datetime('now'));";

    /**
     * Inserts Log Out date into the logins table.
     * Parameters: user_ID (int)
     */
    public static final String ADD_LOGOUT_DATE = "INSERT INTO logins (user_ID, logout_Time) VALUES (?, datetime('now'));";

    /**
     * Inserts a transaction into the masterRecord table:
     * Parameters sender_ID(int), receiver_ID(int), funds(int)
     */
    public static final String UPDATE_MASTER_RECORD = "INSERT INTO masterRecord (sender_ID, receiver_ID, sum, date) VALUES (?, ?, ?, datetime('now'));";

    // DATA UPDATES

    /**
     * Updates the balance for an account_ID(int) with a new sum
     * Parameters funds(int), account_ID(int)
     */
    public static final String UPDATE_BALANCE = "UPDATE accounts SET sum = ? WHERE account_ID = ?;";
}
