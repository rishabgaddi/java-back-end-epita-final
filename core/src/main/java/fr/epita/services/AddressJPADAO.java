package fr.epita.services;

import fr.epita.datamodel.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AddressJPADAO implements IDAO<Address> {
    private final SessionFactory sessionFactory;

    public AddressJPADAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Address address) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(address);
        transaction.commit();
        session.close();
    }

    public void update(Address address) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(address);
        transaction.commit();
        session.close();
    }

    public Address findById(Long id) {
        Session session = this.sessionFactory.openSession();
        Address address = session.find(Address.class, id);
        session.close();
        return address;
    }

    public List<Address> findAll() {
        Session session = this.sessionFactory.openSession();
        List<Address> addressList = session.createQuery("from Address", Address.class).getResultList();
        session.close();
        return addressList;
    }

    public void delete(Address address) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(address);
        transaction.commit();
        session.close();
    }
}
