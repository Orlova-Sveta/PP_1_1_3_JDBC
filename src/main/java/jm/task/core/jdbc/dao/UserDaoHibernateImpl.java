package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

import static org.hibernate.resource.transaction.spi.TransactionStatus.ACTIVE;
import static org.hibernate.resource.transaction.spi.TransactionStatus.MARKED_ROLLBACK;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS users(id int primary key auto_increment, name varchar(40), lastName varchar(40), age int );");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE
                    || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS users;");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE
                    || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE
                    || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete User where id = :param");
            query.setParameter("param", id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE
                    || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = Collections.emptyList();
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from User");
            users = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE
                    || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("TRUNCATE TABLE users");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null || transaction.getStatus() == ACTIVE
                    || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        }
    }
}