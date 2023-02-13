package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соединения с БД
    private static final String hostName = "localhost"; // Ссылка до хоста бд
    private static final String dbName = "new_schema"; // Название бд
    private static final String userName = "root"; // логин и пароль для подключения
    private static final String password = "1234";
    private static final String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

    //Hibernate
    private static SessionFactory sessionFactory;

    public static Connection getMySQLConnection() {
        Connection connection;
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

