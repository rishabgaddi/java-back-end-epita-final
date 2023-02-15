package fr.epita.services;

import fr.epita.datamodel.SeenMovie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeenMovieJPADAO implements IDAO<SeenMovie> {
    private final SessionFactory sessionFactory;

    public SeenMovieJPADAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(SeenMovie seenMovie) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(seenMovie);
    }

    @Transactional
    public void update(SeenMovie seenMovie) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(seenMovie);
    }

    @Transactional
    public SeenMovie findById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        SeenMovie seenMovie = session.find(SeenMovie.class, id);
        return seenMovie;
    }

    @Transactional
    public List<SeenMovie> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<SeenMovie> seenMovieList = session.createQuery("from SeenMovie", SeenMovie.class).getResultList();
        return seenMovieList;
    }

    @Transactional
    public List<SeenMovie> findByUser(Long userId) {
        Session session = this.sessionFactory.getCurrentSession();
        List<SeenMovie> seenMovieList = session.createQuery("from SeenMovie where user_id = :user_id", SeenMovie.class)
                .setParameter("user_id", userId)
                .getResultList();
        return seenMovieList;
    }

    @Transactional
    public void delete(SeenMovie seenMovie) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(seenMovie);
    }
}
