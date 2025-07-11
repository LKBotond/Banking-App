CREATE TABLE test(
    id integer
);

CREATE TABLE
    logins (
        user_ID INTEGER,
        login_Time TEXT,
        logout_Time TEXT,
        FOREIGN KEY (user_ID) REFERENCES users (user_ID)
    );

CREATE TABLE
    users (
        user_ID INTEGER PIMARY KEY AUTOINCREMENT NOT NULL,
        email TEXT,
        name_Encrypted TEXT,
        iv TEXT,
        pass_Hash TEXT,
        registered TEXT,
    );

CREATE TABLE
    accounts (
        account_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
        user_ID INTEGER NOT NULL,
        funds INTEGER,
        FOREIGN KEY (user_ID) REFERENCES users (user_ID)
    );

CREATE TABLE
    masterRecord (
        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
        sender_ID INTEGER,
        receiver_ID INTEGER,
        sum INTEGER,
        date TEXT,
        FOREIGN KEY (sender_ID) REFERENCES accounts (account_ID),
        FOREIGN KEY (receiver_ID) REFERENCES accounts (account_ID)
    );