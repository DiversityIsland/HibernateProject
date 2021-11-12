package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {}

    @Override
    public void createUsersTable() {
        Session session = Util.getSession();
        Transaction trans = null;

        try {
            trans = session.beginTransaction();

            session.createSQLQuery("CREATE TABLE IF NOT EXISTS User (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(99) NOT NULL, lastName VARCHAR(99) NOT NULL, age TINYINT NOT NULL, PRIMARY KEY (id))").executeUpdate();

            try {
                trans.commit();
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        } catch (HibernateException e) {
            e.printStackTrace();

            try {
                if (trans != null) {
                    trans.rollback();
                }
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSession();
        Transaction trans = null;

        try {
            trans = session.beginTransaction();

            session.createSQLQuery("DROP TABLE IF EXISTS User").executeUpdate();

            try {
                trans.commit();
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        } catch (HibernateException e) {
            e.printStackTrace();

            try {
                if (trans != null) {
                    trans.rollback();
                }
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSession();
        Transaction trans = null;

        try {
            trans = session.beginTransaction();

            session.save(new User(name, lastName, age));

            try {
                trans.commit();
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }

            System.out.printf("User с именем \"%s\" добавлен в базу данных!", name);
            System.out.println();
        } catch (HibernateException e) {
            e.printStackTrace();

            try {
                if (trans != null) {
                    trans.rollback();
                }
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSession();
        Transaction trans = null;

        try {
            trans = session.beginTransaction();

            session.delete(session.get(User.class, id));

            try {
                trans.commit();
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        } catch (HibernateException e) {
            e.printStackTrace();

            try {
                if (trans != null) {
                    trans.rollback();
                }
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSession();
        List<User> list = null;

        try {
            list = (List<User>) session.createQuery("from User").list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        }

        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession();
        Transaction trans = null;

        try {
            trans = session.beginTransaction();

            session.createQuery("delete from User u").executeUpdate();

            try {
                trans.commit();
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        } catch (HibernateException e) {
            e.printStackTrace();

            try {
                if (trans != null) {
                    trans.rollback();
                }
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        }
    }
}
