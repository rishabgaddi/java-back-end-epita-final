package fr.epita.services;

import fr.epita.datamodel.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactJPADAO implements IDAO<Contact> {
    private final SessionFactory sessionFactory;

    public ContactJPADAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(Contact contact) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(contact);
    }

    @Transactional
    public void update(Contact contact) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(contact);
    }

    @Transactional
    public Contact findById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Contact contact = session.find(Contact.class, id);
        return contact;
    }

    @Transactional
    public List<Contact> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Contact> contactList = session.createQuery("from Contact", Contact.class).getResultList();
        return contactList;
    }

    @Transactional
    public void delete(Contact contact) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(contact);
    }
}
