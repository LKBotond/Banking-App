package maven_try;

import Classes.Helpers.Input;

import java.util.HashMap;
import java.util.Map;
import Classes.DBInterface.*;
import Classes.Security.*;
import Classes.Person.*;
import Classes.accounts.*;

public class App {
    public static void main(String[] args) {
        DBInterface Hope = new DBInterface("src\\main\\db\\app.db");
        Hope.initializeDB();
        PersonDAO person_Fetcher = new PersonDAO(Hope);
        AccountDAO account_Fetcher = new AccountDAO(Hope);
        Authenticator controller = new Authenticator(person_Fetcher, account_Fetcher);

        Input Handler = new Input();
        Map<Integer, Person> activeUsers = new HashMap<>();
        Person test = null;
        System.out.println("Register : 1");
        System.out.println("Login : 2");
        int action = Handler.getInt();
        if (action == 1) {
            System.out.println("Register:");
            System.out.println("Email :");
            String email = Handler.getString();
            System.out.println("Name :");
            String full_Name = Handler.getString();
            // for now use exactly 16 digits
            System.out.println("Pass :");
            String pass = Handler.getString();
            Person registered = controller.registerPerson(email, full_Name, pass);
            activeUsers.put(registered.getUserId(), registered);
            test = registered;
        } else if (action == 2) {
            System.out.println("Login:");
            System.out.println("Email :");
            String email = Handler.getString();
            // for now use exactly 16 digits
            System.out.println("Pass :");
            String pass = Handler.getString();
            Person logged_IN = controller.logInPerson(email, pass);
            activeUsers.put(logged_IN.getUserId(), logged_IN);
            test = logged_IN;
        } else {
            System.out.println("Invalid action specified");
        }

        System.out.println("Add to my Funds : 1");
        System.out.println("Add to Someone else's funds : 2");
        System.out.println("Logout : 3");

        if (action == 1) {
            System.out.println("Adding to my Funds");
            System.out.println("To which account?");
            int account_ID = Handler.getInt();
            Account account= test.getAccountByID(account_ID);
            System.out.println("How Much:");
            int sum = Handler.getInt();
            account.addToFunds(sum);
            account_Fetcher.updateBalanceForAccount(account_ID, sum);


        } else if (action == 2) {
        } else if (action == 3) {

        } else {
            System.out.println("Invalid action specified");
        }

    }

}
// plan / todo
// create the interactions
