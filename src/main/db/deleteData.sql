/*Dellete account*/
DELETE FROM accounts
WHERE
    user_ID = ?
    AND sum = 0;

/*Delete user*/ 
DELETE FROM users
WHERE
    user_ID = ?;
