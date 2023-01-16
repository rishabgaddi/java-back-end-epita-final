package fr.epita.services;

import fr.epita.datamodel.Contact;
import org.hibernate.SessionFactory;

import java.util.List;

public class ContactJPADAO {
    private final SessionFactory sessionFactory;

    public ContactJPADAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Contact contact) {
        sessionFactory.getCurrentSession().save(contact);
    }

    public void update(Contact contact) {
        sessionFactory.getCurrentSession().update(contact);
    }

    public Contact findById(Long id) {
        return sessionFactory.getCurrentSession().find(Contact.class, id);
    }

    public List<Contact> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Contact", Contact.class).list();
    }

    public void delete(Contact contact) {
        sessionFactory.getCurrentSession().delete(contact);
    }
}
