package fr.epita.rest.web.data.services;

import fr.epita.datamodel.Movie;
import fr.epita.rest.web.messages.MovieDTO;
import fr.epita.services.MovieJPADAO;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;

public class MovieDataService {
    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(MovieDataService.class);
    private final MovieJPADAO movieJPADAO;
    private final SessionFactory sessionFactory;

    public MovieDataService(MovieJPADAO movieJPADAO, SessionFactory sessionFactory) {
        this.movieJPADAO = movieJPADAO;
        this.sessionFactory = sessionFactory;
    }

    public void addMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        movie.setAdded(movieDTO.getAdded());
        movie.setExternalId(movieDTO.getExternalId());
        movieJPADAO.save(movie);
    }

}
