package banking;

import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/bankingsystems";
    private static final String USER = "root";
    private static final String PASSWORD = "Sriharsha@2007";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
