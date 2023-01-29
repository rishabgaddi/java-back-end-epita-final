package fr.epita.services;

import fr.epita.datamodel.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressJPADAO implements IDAO<Address> {
    private final SessionFactory sessionFactory;

    public AddressJPADAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(Address address) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(address);
    }

    @Transactional
    public void update(Address address) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(address);
    }

    @Transactional
    public Address findById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Address address = session.find(Address.class, id);
        return address;
    }

    @Transactional
    public List<Address> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Address> addressList = session.createQuery("from Address", Address.class).getResultList();
        return addressList;
    }

    @Transactional
    public void delete(Address address) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(address);
    }
}
