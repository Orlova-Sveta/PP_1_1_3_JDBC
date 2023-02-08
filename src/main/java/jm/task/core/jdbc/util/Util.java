package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соединения с БД

    public static Connection getMySQLConnection() {
        String hostName = "localhost";

        String dbName = "new_schema";
        String userName = "root";
        String password = "1234";

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        return getConnection(connectionURL, userName, password);
    }

    private static Connection getConnection(String connectionUrl, String userName,
                                            String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection;
        try {
            connection = DriverManager.getConnection(connectionUrl, userName, password);
            return connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}

