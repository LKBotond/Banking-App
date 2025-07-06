package maven_try;

import Classes.DBInterface.*;

public class App {
    public static void main(String[] args) {
        DBInterface Hope = new DBInterface("src\\main\\db\\app.db");
        Hope.createTable(DBQueries.CREATE_LOGIN_TABLE);
        

    }
}
