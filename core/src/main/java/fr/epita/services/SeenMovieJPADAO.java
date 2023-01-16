package fr.epita.services;

import fr.epita.datamodel.SeenMovie;
import org.hibernate.SessionFactory;

import java.util.List;

public class SeenMovieJPADAO {
    private final SessionFactory sessionFactory;

    public SeenMovieJPADAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(SeenMovie seenMovie) {
        sessionFactory.getCurrentSession().save(seenMovie);
    }

    public void update(SeenMovie seenMovie) {
        sessionFactory.getCurrentSession().update(seenMovie);
    }

    public SeenMovie findById(Long id) {
        return sessionFactory.getCurrentSession().find(SeenMovie.class, id);
    }

    public List<SeenMovie> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from SeenMovie", SeenMovie.class).list();
    }

    public void delete(SeenMovie seenMovie) {
        sessionFactory.getCurrentSession().delete(seenMovie);
    }
}
