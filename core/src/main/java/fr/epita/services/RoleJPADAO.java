package fr.epita.services;

import fr.epita.datamodel.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class RoleJPADAO {
    private final SessionFactory sessionFactory;

    public RoleJPADAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Role role) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(role);
        transaction.commit();
    }

    public void update(Role role) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(role);
        transaction.commit();
    }

    public Role findById(Long id) {
        Session session = this.sessionFactory.openSession();
        return session.find(Role.class, id);
    }

    public Role findByName(String name) {
        Session session = this.sessionFactory.openSession();
        return session.createQuery("from Role r where r.name = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<Role> findAll() {
        Session session = this.sessionFactory.openSession();
        return session.createQuery("from Role", Role.class).getResultList();
    }

    public void delete(Role role) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(role);
        transaction.commit();
    }
}
