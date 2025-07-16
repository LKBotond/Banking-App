package Classes.DBInterface;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

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
        try (PreparedStatement stmt = connection.prepareStatement(structure)) {
            stmt.execute();
        } catch (SQLException exception) {
            System.out.println("error: " + exception);
        }
    }

    // complex input methods
    public void insertData(String query, List<Object> data) {
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
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
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
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
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
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
    public ArrayList<Integer> getIntArrayList(String query, List<Object> keys) {
        ArrayList<Integer> result = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
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

    public ArrayList<String> getStringArrayList(String query, List<Object> keys) {
        ArrayList<String> result = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
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
                result.add(rs.getString(1));
            }
            return result;

        } catch (SQLException exception) {
            System.out.println("error: " + exception);
        }
        return result;
    }

    public ArrayList<String> getStringArrayListForRow(String query, List<Object> keys, List<String> columns) {
        ArrayList<String> result = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
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
            ResultSetMetaData metaData = rs.getMetaData();
            if (rs.next()) {
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    if (columns.contains(metaData.getColumnName(i))) {
                        result.add(rs.getString(i));
                    }
                }
            }

            return result;

        } catch (SQLException exception) {
            System.out.println("error: " + exception);
        }
        return result;

    }

    public boolean checkForTable(String name) {
        try (PreparedStatement pstmt = connection.prepareStatement(DBQueries.QUERY_FOR_TABLE_NAME)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public void initializeDB() {
        ArrayList<String> tables = DBQueries.getTableNames();
        ArrayList<String> creator_Queries = DBQueries.tableCreatorStrings();
        for (int i = 0; i < tables.size(); i++) {
            if (!checkForTable(tables.get(i))) {
                createTable(creator_Queries.get(i));
            }
        }
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
