package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Root#1234";

    public static Connection getConnect() throws SQLException {
        Connection connection = null;

        while (connection == null || connection.isClosed()) {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }

        return connection;
    }

    public static Session getSession() throws SQLException {
        Properties properties = new Properties();
        Configuration cfg = new Configuration();

        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        //properties.setProperty(Environment.HBM2DDL_AUTO,"create-drop");
        properties.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.setProperty(Environment.USER, USERNAME);
        properties.setProperty(Environment.PASS, PASSWORD);
        properties.setProperty(Environment.URL, URL);
        cfg.setProperties(properties);
        cfg.addAnnotatedClass(User.class);
        SessionFactory factory = cfg.buildSessionFactory();

        return factory.openSession();
    }
}