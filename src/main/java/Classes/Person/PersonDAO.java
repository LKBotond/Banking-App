package Classes.Person;

import java.sql.SQLException;
import java.util.ArrayList;
import Classes.DBInterface.*;
import Classes.Helpers.Utils;

public class PersonDAO {
    DBInterface connection;

    public PersonDAO(DBInterface connection) {
        this.connection = connection;
    }

    public boolean checkForPersonByEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        try {
            return connection.getString(DBQueries.GET_EMAIL_FOR_AUTHENTICATION, Utils.objectListify(email)) != null;
        } catch (SQLException e) {
            System.out.println("Failed check for user: " + e);
            return false;
        }
    }

    public String getPassByEmail(String email) {
        try {
            return connection.getString(DBQueries.GET_PASS_FOR_AUTHENTICATION, Utils.objectListify(email));
        } catch (SQLException e) {
            System.out.println("Failed to get password by email: " + e);
            return new String();
        }
    }

    public Person getPersonByEmail(String email) {
        try {
            ArrayList<String> needed_Columns = new ArrayList<>();
            needed_Columns.add("user_ID");
            needed_Columns.add("name_Encrypted");
            needed_Columns.add("iv");
            ArrayList<String> identifiers = connection.getStringArrayListForRow(DBQueries.GET_USER,
                    Utils.objectListify(email), needed_Columns);
            Integer user_ID = Utils.stringToInt(identifiers.get(0));
            String name_Encrypted = identifiers.get(1);
            String IV = identifiers.get(2);
            return new Person(user_ID, email, name_Encrypted, IV);
        } catch (SQLException e) {
            System.out.println("Failed to get user data for Person: " + e);
            return Person.empty();
        }

    }

    public void addPerson(String email, String name_Encrypted, String IV, String pass) {
        try {
            ArrayList<Object> credentials = new ArrayList<>();
            credentials.add(email);
            credentials.add(name_Encrypted);
            credentials.add(IV);
            credentials.add(pass);

            connection.insertData(DBQueries.ADD_USER, credentials);

        } catch (SQLException e) {
            System.out.println("Failed to add new useer to db: " + e);

        }
    }

    public void addLoginDate(int user_ID) {
        try {
            connection.insertData(DBQueries.ADD_LOGIN_DATE, Utils.objectListify(user_ID));
        } catch (SQLException e) {
            System.out.println("Failed to add login date: " + e);
        }
    }

    public void addLogoutDate(int user_ID) {
        try {
            connection.insertData(DBQueries.ADD_LOGOUT_DATE, Utils.objectListify(user_ID));
        } catch (SQLException e) {
            System.out.println("Failed to add logout date: " + e);
        }
    }
}
