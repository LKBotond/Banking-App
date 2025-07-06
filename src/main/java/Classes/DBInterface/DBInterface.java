package Classes.DBInterface;

import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DBInterface {
    String path_Root = "jdbc:sqlite:";
    private Connection connection;

    public DBInterface(String path_Param) {
        try {
            connection = DriverManager.getConnection(path_Root + path_Param);
        } catch (SQLException exception) {
            System.out.println("error: " + exception);
        }
    }

    // simple input methods
    public void createTable(String structure) {
        try {
            PreparedStatement stmt = connection.prepareStatement(structure);
            stmt.execute();
        } catch (SQLException exception) {
            System.out.println("error: " + exception);
        }
    }

    // complex input methods
    public void insertData(String query, List<Object> data) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            for (int i = 0; i < data.size(); i++) {
                Object item = data.get(i);
                if (item instanceof Integer) {
                    pstmt.setInt(i + 1, (Integer) item);
                } else if (item instanceof Double) {
                    pstmt.setDouble(i + 1, (Double) item);
                } else if (item instanceof String) {
                    pstmt.setString(i + 1, (String) item);
                }
            }
            pstmt.execute();
        } catch (SQLException error) {
            System.out.println(error);
        }
    }

    // Simple output methods
    public String getString(String query, List<Object> keys) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            for (int i = 0; i < keys.size(); i++) {
                Object item = keys.get(i);
                if (item instanceof Integer) {
                    pstmt.setInt(i + 1, (Integer) item);
                } else if (item instanceof Double) {
                    pstmt.setDouble(i + 1, (Double) item);
                } else if (item instanceof String) {
                    pstmt.setString(i + 1, (String) item);
                }
            }
            ResultSet rs = pstmt.executeQuery();
            return rs.next() ? rs.getString(1) : null;
        } catch (SQLException exception) {
            System.out.println("error: " + exception);
        }
        return null;
    }

    public int getInt(String query, List<Object> keys) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            for (int i = 0; i < keys.size(); i++) {
                Object item = keys.get(i);
                if (item instanceof Integer) {
                    pstmt.setInt(i + 1, (Integer) item);
                } else if (item instanceof Double) {
                    pstmt.setDouble(i + 1, (Double) item);
                } else if (item instanceof String) {
                    pstmt.setString(i + 1, (String) item);
                }
            }
            ResultSet rs = pstmt.executeQuery();
            return rs.next() ? rs.getInt(1) : null;
        } catch (SQLException exception) {
            System.out.println("error: " + exception);
        }
        return 0;
    }

    // Complex output methods
    public ArrayList<Integer> getIntArrayLIst(String query, List<Object> keys) {
        ArrayList<Integer> result = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            for (int i = 0; i < keys.size(); i++) {
                Object item = keys.get(i);
                if (item instanceof Integer) {
                    pstmt.setInt(i + 1, (Integer) item);
                } else if (item instanceof Double) {
                    pstmt.setDouble(i + 1, (Double) item);
                } else if (item instanceof String) {
                    pstmt.setString(i + 1, (String) item);
                }
            }
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result.add(rs.getInt(1));
            }
            return result;
        } catch (SQLException exception) {
            System.out.println("error: " + exception);
        }
        return result;

    }

    public ArrayList<String> getStringArrayLIst(String query) {
        ArrayList<String> result = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                result.add(rs.getString(1));
            }
        } catch (SQLException exception) {
            System.out.println("error: " + exception);
        }
        return result;

    }

    private Object determineDatatype(Object input) {
        if (input instanceof Integer) {
            return Integer.class;
        } else if (input instanceof Double) {
            return Double.class;
        } else if (input instanceof String) {
            return String.class;
        } else if (input instanceof Float) {
            return Float.class;
        } else if (input instanceof Long) {
            return Long.class;
        } else if (input instanceof Boolean) {
            return Boolean.class;
        }
        return Object.class;
    }

}
