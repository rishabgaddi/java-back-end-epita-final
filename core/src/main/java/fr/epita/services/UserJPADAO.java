package fr.epita.services;

import fr.epita.datamodel.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserJPADAO implements IDAO<User> {
    private final SessionFactory sessionFactory;

    public UserJPADAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Transactional
    public void update(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Transactional
    public User findById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = session.find(User.class, id);
        return user;
    }

    @Transactional
    public List<User> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User", User.class).getResultList();
        return userList;
    }

    @Transactional
    public void delete(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(user);
    }
}
