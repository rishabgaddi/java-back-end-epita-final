package fr.epita.services;

import fr.epita.datamodel.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieJPADAO implements IDAO<Movie> {
    private final SessionFactory sessionFactory;

    public MovieJPADAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(Movie movie) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(movie);
    }

    @Transactional
    public void update(Movie movie) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(movie);
    }

    @Transactional
    public Movie findById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Movie movie = session.find(Movie.class, id);
        return movie;
    }

    @Transactional
    public Movie findByExternalId(String externalId) {
        Session session = this.sessionFactory.getCurrentSession();
        Movie movie = session.createQuery("from Movie where externalId = :externalId", Movie.class)
                .setParameter("externalId", externalId)
                .getSingleResult();
        return movie;
    }

    @Transactional
    public List<Movie> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Movie> movieList = session.createQuery("from Movie", Movie.class).getResultList();
        return movieList;
    }

    @Transactional
    public void delete(Movie movie) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(movie);
    }
}
