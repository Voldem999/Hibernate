//package jm.task.core.jdbc.util;
//
//import jm.task.core.jdbc.model.User;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.Properties;
//
//public class Util {
//    // реализуйте настройку соединение с БД
//
//        private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
//    private static final String USERNAME = "student";
//    private static final String PASSWORD = "chocolatefrog";
//
//    public static Connection getConnection() {
//
//        try {
//            System.out.println("Соединение с БД установлено");
//            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
//
//        } catch (SQLException e) {
//            System.err.println("Соединение с БД не установлено");
//        }
//        return null;
//    }
//}
package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    public static final Properties PROPERTIES = new Properties();
    public static Connection getConnection(){
        try (InputStream inputStream = Util.class.getClassLoader().getResourceAsStream("hibernate.properties");){
            PROPERTIES.load(inputStream);
            return DriverManager.getConnection(
                    PROPERTIES.getProperty("hibernate.connection.url"),
                    PROPERTIES.getProperty("hibernate.connection.username"),
                    PROPERTIES.getProperty("hibernate.connection.password")
            );
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Session getSessionFactory() {
        SessionFactory sessionFactory = new Configuration().addAnnotatedClass(User.class).buildSessionFactory();

        return sessionFactory.openSession();
    }
}
