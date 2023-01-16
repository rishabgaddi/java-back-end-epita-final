package fr.epita.services;

import fr.epita.datamodel.Movie;
import org.hibernate.SessionFactory;

import java.util.List;

public class MovieJPADAO {
    private final SessionFactory sessionFactory;

    public MovieJPADAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Movie movie) {
        sessionFactory.getCurrentSession().save(movie);
    }

    public void update(Movie movie) {
        sessionFactory.getCurrentSession().update(movie);
    }

    public Movie findById(Long id) {
        return sessionFactory.getCurrentSession().find(Movie.class, id);
    }

    public List<Movie> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Movie", Movie.class).list();
    }

    public void delete(Movie movie) {
        sessionFactory.getCurrentSession().delete(movie);
    }
}
