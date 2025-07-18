package Classes.Transaction;

import java.sql.SQLException;

import Classes.DBInterface.DBInterface;

public class TransactionDAO {
    DBInterface connection;

    public TransactionDAO(DBInterface connection) {
        this.connection = connection;
    }

    public void setAutoCommit(boolean state) {
        try {
            connection.setAutoCommit(state);
        } catch (SQLException e) {
            System.out.println("Failed to set Autocommit to: " + state + " error: " + e);
        }
    }

    public void commitChanges() {
        try {
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Failed to commit changes: " + e);
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println("Failed to set rollback changes: " + e);
        }
    }

}
