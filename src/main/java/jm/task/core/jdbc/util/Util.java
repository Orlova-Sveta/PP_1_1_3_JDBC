package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соединения с БД
    private static final String hostName = "localhost"; // Ссылка до хоста бд
    private static final String dbName = "new_schema"; // Название бд
    private static final String userName = "root"; // логин и пароль для подключения
    private static final String password = "1234";

    public static Connection getMySQLConnection() {
        Connection connection;
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionURL, userName, password);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

