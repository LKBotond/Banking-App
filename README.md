# Banking App

## TLDR

This is my first ever java project, its a backend for a banking application. For database interactions I've utilised SQLite3. For user authentication and password hashing, Argon2.

## Files

### DB acces and IO:

**src/main/java/Classes/DBInterface/DBQueries.java :** Holds String templates for SQL queries.
**src/main/java/Classes/DBInterface/DBInterface.java :** Holds methods to get data in and out of the database.
**src/main/java/Classes/accounts/AccountDAO.java :** Applies DBInterface's methods to get Account specific data in and out of the database.
**src/main/java/Classes/Person/PersonDAO.java :** Applies DBInterface's methods to get User specific data in and out of the database.

### Security / Authentication:

**src/main/java/Classes/Security/Encrypt.java :** Holds methods to Encrypt, Decrypt and Hash data.
**src/main/java/Classes/Security/Authenticator.java :** Holds methods for the Validation, Login, and registration processes.

### Data Transfer:

**src/main/java/Classes/Transaction/Transaction.java :** contains methods to facilitate and log transactions between accounts.

### Holders:

**src/main/java/Classes/accounts/Account.java :** Holds account_ID and funds, ascociated with said ID, contains methods to add or remove from said funds.

**src/main/java/Classes/Person/Person.java :** Holds User specific information (ID, name, accounts, cards{to be added}).

### Helpers:

**src/main/java/Classes/Helpers/Input.java :** Wrapper to get CLI inputs.
**src/main/java/Classes/Helpers/TimeH.java :** Contains methods to get current time or time in the future.
**src/main/java/Classes/Helpers/Utils.java :** Contains helper methods to convert between data types.

## Roadmap:

1.  Currently authentication rellies on a password being exactly 16 bytes in length, so adding a propper key derivation (PBKDF2) is also on the horizon.
2.  Currently transactions are handled between account ID's, I'm going to add card number generation /authentication and association as well.
3.  Rudimentary front end integration with secure communication
