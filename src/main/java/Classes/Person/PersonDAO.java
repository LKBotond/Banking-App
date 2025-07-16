package Classes.Person;

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
        return connection.getString(DBQueries.GET_EMAIL_FOR_AUTHENTICATION, Utils.objectListify(email)) != null;
    }

    public String getPassByEmail(String email) {
        return connection.getString(DBQueries.GET_PASS_FOR_AUTHENTICATION,
                Utils.objectListify(email));
    }

    public Person getPersonByEmail(String email) {
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
    }

    public void addPerson(String email, String name_Encrypted, String IV, String pass) {
        ArrayList<Object> credentials = new ArrayList<>();
        credentials.add(email);
        credentials.add(name_Encrypted);
        credentials.add(IV);
        credentials.add(pass);

        connection.insertData(DBQueries.ADD_USER, credentials);
    }
}
