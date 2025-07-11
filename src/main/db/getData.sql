/*Get User after logged in*/
SELECT
    user_ID,
    name_Encrypted,
    iv
FROM
    users
WHERE
    email = ?;

/*get user for authentication*/
SELECT
    pass_Hash
FROM
    users
WHERE
    email = ?;

/*Get Email for User*/
SELECT
    email
FROM
    users
WHERE
    email = ?;

/*Get accounts for user*/
SELECT
    account_ID
FROM
    accounts
WHERE
    (user_ID = ?);

/*Get Balance*/
SELECT
    funds
FROM
    accounts
WHERE
    (account_ID = ?);

/*Get records for account*/
SELECT
    *
FROM
    masterRecord
WHERE
    (sender_ID = ?);

SELECT
    *
FROM
    masterRecord
WHERE
    (receiver_ID = ?);

/*Get records between 2 accounts*/
SELECT
    *
FROM
    masterRecord
WHERE
    (sender_ID = ?, receiver_ID = ?);

/*Join a user to its taransactions*/
SELECT
    *
FROM
    masterRecord
WHERE
    sender_ID IN (
        account_ID =
        SELECT
            account_ID
        FROM
            accounts
        WHERE
            user_ID = ?
    );