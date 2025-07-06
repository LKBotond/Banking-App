/*Get User*/
SELECT
    user_ID,
    account_ID
FROM
    users
WHERE
    (
        name_Hash = ?
        AND pass_Hash = ?
    );

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