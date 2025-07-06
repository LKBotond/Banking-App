/*Delete user*/
DELETE FROM accounts
WHERE
    user_ID = ?
    AND sum = 0;

DELETE FROM users
WHERE
    user_ID = ?;
