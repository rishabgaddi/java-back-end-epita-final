package fr.epita.services;

import fr.epita.datamodel.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleJPADAO implements IRoleDAO {
    private final SessionFactory sessionFactory;

    public RoleJPADAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(Role role) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(role);
    }

    @Transactional
    public void update(Role role) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(role);
    }

    @Transactional
    public Role findById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Role role = session.find(Role.class, id);
        return role;
    }

    @Transactional
    public Role findByName(String name) {
        Session session = this.sessionFactory.getCurrentSession();
        Role role = session.createQuery("from Role r where r.name = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
        return role;
    }

    @Transactional
    public List<Role> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Role> roleList = session.createQuery("from Role", Role.class).getResultList();
        return roleList;
    }

    @Transactional
    public void delete(Role role) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(role);
    }
}
