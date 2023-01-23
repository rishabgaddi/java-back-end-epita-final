package fr.epita.services;

import fr.epita.datamodel.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ContactJPADAO implements IDAO<Contact> {
    private final SessionFactory sessionFactory;

    public ContactJPADAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Contact contact) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(contact);
        transaction.commit();
        session.close();
    }

    public void update(Contact contact) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(contact);
        transaction.commit();
        session.close();
    }

    public Contact findById(Long id) {
        Session session = this.sessionFactory.openSession();
        Contact contact = session.find(Contact.class, id);
        session.close();
        return contact;
    }

    public List<Contact> findAll() {
        Session session = this.sessionFactory.openSession();
        List<Contact> contactList = session.createQuery("from Contact", Contact.class).getResultList();
        session.close();
        return contactList;
    }

    public void delete(Contact contact) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(contact);
        transaction.commit();
        session.close();
    }
}
