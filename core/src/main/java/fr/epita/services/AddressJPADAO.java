package fr.epita.services;

import fr.epita.datamodel.Address;
import org.hibernate.SessionFactory;

import java.util.List;

public class AddressJPADAO {
    private final SessionFactory sessionFactory;

    public AddressJPADAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Address address) {
        sessionFactory.getCurrentSession().save(address);
    }

    public void update(Address address) {
        sessionFactory.getCurrentSession().update(address);
    }

    public Address findById(Long id) {
        return sessionFactory.getCurrentSession().find(Address.class, id);
    }

    public List<Address> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Address", Address.class).list();
    }

    public void delete(Address address) {
        sessionFactory.getCurrentSession().delete(address);
    }
}
