package db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance = new DBConnection();
    public static Connection con = null;

    public static DBConnection getInstance() {
        return instance;
    }

    public void connect() throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyCafe;trustServerCertificate=true";
        String user = "sa";
        String password = "12";
        con = DriverManager.getConnection(url, user, password);
        if (con != null) {
        }
    }

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        return con;
    }

}
