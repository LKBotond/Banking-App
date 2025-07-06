/*Create User*/
INSERT INTO
    users (name_Hash, pass_Hash)
VALUES
    (?, ?);

/*Create account*/
INSERT INTO
    accounts (user_ID, funds)
VALUES
    (?, ?);

/*Logs*/
INSERT INTO
    logins (user_ID, login_Time)
VALUES
    (?, datetime ('now'));

INSERT INTO
    logins (user_ID, logout_Time)
VALUES
    (?, datetime ('now'));

INSERT INTO
    masterRecord (sender_ID, receiver_ID, sum, date)
VALUES
    (?, ?, ?, datetime ('now'));