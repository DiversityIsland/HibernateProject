package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction trans = null;
        Query query = null;

        try (Session session = Util.getSession()) {
            trans = session.beginTransaction();
            query = session.createSQLQuery("CREATE TABLE User (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(99) NOT NULL, lastName VARCHAR(99) NOT NULL, age TINYINT NOT NULL, PRIMARY KEY (id))");
            query.executeUpdate();
            trans.commit();
        } catch (Exception e) {

        }
    }

    @Override
    public void dropUsersTable() {
        Transaction trans = null;
        Query query = null;

        try (Session session = Util.getSession()) {
            trans = session.beginTransaction();
            query = session.createSQLQuery("DROP TABLE User");
            query.executeUpdate();
            trans.commit();
        } catch (Exception e) {

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction trans = null;

        try (Session session = Util.getSession()) {
            trans = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.persist(user);
            trans.commit();
        } catch (SQLException e) {

        }

        System.out.printf("User с именем \"%s\" добавлен в базу данных!", name);
        System.out.println();
    }

    @Override
    public void removeUserById(long id) {
        Transaction trans = null;
        Query query = null;

        try (Session session = Util.getSession()) {
            trans = session.beginTransaction();
            query = session.createQuery("delete from User u where u.id=?1");
            query.setParameter(1, id);
            query.executeUpdate();
            trans.commit();
        } catch (SQLException e) {

        }
    }

    @Override
    public List<User> getAllUsers() {
        Query<User> query = null;
        List<User> list = null;

        try (Session session = Util.getSession()) {
            query = session.createQuery("from User");
            list = query.list();
        } catch (SQLException e) {

        }

        return list;
    }

    @Override
    public void cleanUsersTable() {
        Transaction trans = null;
        Query query = null;

        try (Session session = Util.getSession()) {
            trans = session.beginTransaction();
            query = session.createQuery("delete from User u");
            query.executeUpdate();
            trans.commit();
        } catch (SQLException e) {

        }
    }
}
