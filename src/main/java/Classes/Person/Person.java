package Classes.Person;

import java.util.ArrayList;

import Classes.accounts.*;

public class Person {
    private int user_ID;
    private String email;
    private String encrypted_Name;
    private String IV;
    private String name;
    private String family_Name;
    private ArrayList<Account> accounts;

    public Person(int user_ID, String email, String name, String IV) {
        this.user_ID = user_ID;
        this.email = email;
        this.encrypted_Name = name;
        this.IV = IV;

    }

    public void setAccountsForUser(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public void addNewAccountToUser(Account account) {
        this.accounts.add(account);
    }

    public void splitName(String full_Name) {
        String[] parts = full_Name.split(" ", 2);
        this.name = parts[0];
        if (parts.length > 1) {
            this.family_Name = parts[1];
        } else {
            this.family_Name = "";
        }

    }

    public String getEncryptedName() {
        return this.encrypted_Name;
    }

    public String getIV() {
        return this.IV;
    }

    public ArrayList<String> getEncryptedNameAndIv() {
        ArrayList<String> name_And_IV = new ArrayList<>();
        name_And_IV.add(this.encrypted_Name);
        name_And_IV.add(this.IV);
        return name_And_IV;

    }

    public String getName() {
        return this.name;
    }

    public String getFamilyName() {
        return this.family_Name;
    }

    public String getEmail() {
        return this.email;
    }

    public int getUserId() {
        return this.user_ID;
    }

}
