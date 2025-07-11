/*Create User*/
INSERT INTO
    users (email, name_Encrypted, iv, pass_Hash, registered)
VALUES
    (?, ?, ?, ?, DATETIME ('now'));

/*Create account*/
INSERT INTO
    accounts (user_ID, funds)
VALUES
    (?, ?);

/*Logs*/
INSERT INTO
    logins (user_ID, login_Time)
VALUES
    (?, DATETIME ('now'));

INSERT INTO
    logins (user_ID, logout_Time)
VALUES
    (?, DATETIME ('now'));

INSERT INTO
    masterRecord (sender_ID, receiver_ID, sum, date)
VALUES
    (?, ?, ?, DATETIME ('now'));