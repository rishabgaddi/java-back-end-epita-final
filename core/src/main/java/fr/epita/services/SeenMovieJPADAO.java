package fr.epita.services;

import fr.epita.datamodel.SeenMovie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class SeenMovieJPADAO implements IJPADAO<SeenMovie> {
    private final SessionFactory sessionFactory;

    public SeenMovieJPADAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(SeenMovie seenMovie) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(seenMovie);
        transaction.commit();
        session.close();
    }

    public void update(SeenMovie seenMovie) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(seenMovie);
        transaction.commit();
        session.close();
    }

    public SeenMovie findById(Long id) {
        Session session = this.sessionFactory.openSession();
        SeenMovie seenMovie = session.find(SeenMovie.class, id);
        session.close();
        return seenMovie;
    }

    public List<SeenMovie> findAll() {
        Session session = this.sessionFactory.openSession();
        List<SeenMovie> seenMovieList = session.createQuery("from SeenMovie", SeenMovie.class).getResultList();
        session.close();
        return seenMovieList;
    }

    public void delete(SeenMovie seenMovie) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(seenMovie);
        transaction.commit();
        session.close();
    }
}
