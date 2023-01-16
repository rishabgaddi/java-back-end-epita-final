package fr.epita.services;

import fr.epita.datamodel.User;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserJPADAO {
    private final SessionFactory sessionFactory;

    public UserJPADAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    public User findById(Long id) {
        return sessionFactory.getCurrentSession().find(User.class, id);
    }

    public List<User> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from User", User.class).list();
    }

    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }
}
