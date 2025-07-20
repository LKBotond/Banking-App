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

    public boolean commitChanges() {
        try {
            connection.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to commit changes: " + e);
            return false;
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
