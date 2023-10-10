package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

@SuppressWarnings("ALL")
public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String sqlQuery = "CREATE TABLE IF NOT EXISTS person " +
                "(ID SERIAL PRIMARY KEY," +
                " NAME TEXT, " +
                "LASTNAME TEXT, " +
                "AGE INT)";
        Session session = Util.getSessionFactory();
        session.beginTransaction();
        session.createSQLQuery(sqlQuery).executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory();
        session.beginTransaction();
        session.createNativeQuery("DROP TABLE IF EXISTS person").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Session session = Util.getSessionFactory();
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
        System.out.println("User " + name + " был добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.remove(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory();
        session.beginTransaction();
        List<User> userList = session.createQuery("SELECT user FROM User user", User.class).getResultList();
        session.getTransaction().commit();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory();
        session.beginTransaction();
        session.createNativeQuery("TRUNCATE TABLE person").executeUpdate();
        session.getTransaction().commit();
    }
}
