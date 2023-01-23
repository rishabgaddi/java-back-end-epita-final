package fr.epita.services;

import fr.epita.datamodel.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleJPADAO implements IJPADAO<Role> {
    private final SessionFactory sessionFactory;

    public RoleJPADAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(Role role) {
//        Session session = this.sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        session.save(role);
//        transaction.commit();
//        session.close();
        Session session = this.sessionFactory.getCurrentSession();
        session.save(role);
    }

    public void update(Role role) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(role);
        transaction.commit();
        session.close();
    }

    public Role findById(Long id) {
        Session session = this.sessionFactory.openSession();
        Role role = session.find(Role.class, id);
        session.close();
        return role;
    }

    public Role findByName(String name) {
        Session session = this.sessionFactory.openSession();
        Role role = session.createQuery("from Role where name = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
        session.close();
        return role;
    }

    public List<Role> findAll() {
        Session session = this.sessionFactory.openSession();
        List<Role> roleList = session.createQuery("from Role", Role.class).getResultList();
        session.close();
        return roleList;
    }

    public void delete(Role role) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(role);
        transaction.commit();
        session.close();
    }
}
