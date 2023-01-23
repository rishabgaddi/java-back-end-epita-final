package fr.epita.services;

import fr.epita.datamodel.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class MovieJPADAO implements IJPADAO<Movie> {
    private final SessionFactory sessionFactory;

    public MovieJPADAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Movie movie) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(movie);
        transaction.commit();
        session.close();
    }

    public void update(Movie movie) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(movie);
        transaction.commit();
        session.close();
    }

    public Movie findById(Long id) {
        Session session = this.sessionFactory.openSession();
        Movie movie = session.find(Movie.class, id);
        session.close();
        return movie;
    }

    public List<Movie> findAll() {
        Session session = this.sessionFactory.openSession();
        List<Movie> movieList = session.createQuery("from Movie", Movie.class).getResultList();
        session.close();
        return movieList;
    }

    public void delete(Movie movie) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(movie);
        transaction.commit();
        session.close();
    }
}
