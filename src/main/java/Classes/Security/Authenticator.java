package Classes.Security;

import java.util.ArrayList;
import Classes.Person.PersonDAO;
import Classes.Person.Person;
import Classes.accounts.Account;
import Classes.accounts.AccountDAO;

public class Authenticator {
    Encrypt engine = new Encrypt();

    PersonDAO person_Fetcher;
    AccountDAO account_Fetcher;

    public Authenticator(PersonDAO person_Fetcher, AccountDAO account_Fetcher) {
        this.person_Fetcher = person_Fetcher;
        this.account_Fetcher = account_Fetcher;
    }

    public boolean userInDataBase(String email) {
        if (person_Fetcher.getPassByEmail(email) != null) {
            return true;
        }
        return false;
    }

    public boolean passwordChecksOut(String email, String pass) {
        String pass_On_Record = person_Fetcher.getPassByEmail(email);
        return engine.verifyPass(pass_On_Record, pass);
    }

    public Person registerPerson(String email, String full_Name, String pass) {

        ArrayList<String> encrypted_Name_And_IV = engine.encryptWithAESGCM(full_Name, pass);
        String encrypted_Name = encrypted_Name_And_IV.get(0);
        String IV = encrypted_Name_And_IV.get(1);

        String pass_Hash = engine.hashWithArgon2(pass);

        person_Fetcher.addPerson(email, encrypted_Name, IV, pass_Hash);

        Person current = person_Fetcher.getPersonByEmail(email);

        current.splitName(full_Name);
        account_Fetcher.addAccountToPerson(current.getUserId(), 0);

        ArrayList<Integer> account_ID_List = account_Fetcher.getAccountsByUserID(current.getUserId());
        ArrayList<Account> account_List = new ArrayList<>();
        for (int ID : account_ID_List) {

            int balance = account_Fetcher.getBalanceForAccount(ID);
            account_List.add(new Account(ID, balance));

        }
        current.setAccountsForUser(account_List);

        return current;
    }

    public Person logInPerson(String email, String pass) {

        if (!userInDataBase(email) || !passwordChecksOut(email, pass)) {
            return null;
        }

        Person current = person_Fetcher.getPersonByEmail(email);

        String decrypted_Name = engine.decryptGCM(current.getEncryptedNameAndIv(), pass);
        current.splitName(decrypted_Name);
        ArrayList<Integer> account_ID_List = account_Fetcher.getAccountsByUserID(current.getUserId());
        ArrayList<Account> account_List = new ArrayList<>();
        for (int ID : account_ID_List) {

            int balance = account_Fetcher.getBalanceForAccount(ID);
            account_List.add(new Account(ID, balance));

        }
        current.setAccountsForUser(account_List);

        return current;
    }
}
