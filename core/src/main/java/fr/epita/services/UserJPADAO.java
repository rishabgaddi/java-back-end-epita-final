package fr.epita.services;

import fr.epita.datamodel.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserJPADAO implements IDAO<User> {
    private final SessionFactory sessionFactory;

    public UserJPADAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public void update(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    public User findById(Long id) {
        Session session = this.sessionFactory.openSession();
        User user = session.find(User.class, id);
        session.close();
        return user;
    }

    public List<User> findAll() {
        Session session = this.sessionFactory.openSession();
        List<User> userList = session.createQuery("from User", User.class).getResultList();
        session.close();
        return userList;
    }

    public void delete(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }
}
