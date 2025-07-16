package maven_try;

import Classes.Helpers.Input;
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

        System.out.println("Register : 1");
        System.out.println("Login : 2");
        System.out.println("Logout : 3");
        int action = Handler.getInt();
        if (action == 1) {
            System.out.println("Email :");
            String email = Handler.getString();
            System.out.println("Name :");
            String full_Name = Handler.getString();
            //for now use exactly 16 digits1
            System.out.println("Pass :");

            String pass = Handler.getString();
            controller.registerPerson(email, full_Name, pass);
        }

    }

}
// plan / todo
// create the interactions
