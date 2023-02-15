package fr.epita.rest.web.data.services;

import fr.epita.datamodel.Movie;
import fr.epita.datamodel.SeenMovie;
import fr.epita.datamodel.User;
import fr.epita.rest.web.messages.SeenMovieDTO;
import fr.epita.services.MovieJPADAO;
import fr.epita.services.SeenMovieJPADAO;
import fr.epita.services.UserJPADAO;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class SeenMovieDataService {
    private final SeenMovieJPADAO seenMovieJPADAO;
    private final UserJPADAO userJPADAO;
    private final MovieJPADAO movieJPADAO;
    private final SessionFactory sessionFactory;

    public SeenMovieDataService(SeenMovieJPADAO seenMovieJPADAO, UserJPADAO userJPADAO, MovieJPADAO movieJPADAO, SessionFactory sessionFactory) {
        this.seenMovieJPADAO = seenMovieJPADAO;
        this.userJPADAO = userJPADAO;
        this.movieJPADAO = movieJPADAO;
        this.sessionFactory = sessionFactory;
    }

    public void addSeenMovie(SeenMovieDTO seenMovieDTO) {
        SeenMovie seenMovie = new SeenMovie();
        User user = userJPADAO.find(seenMovieDTO.getUsername());
        Movie movie = movieJPADAO.findByExternalId(seenMovieDTO.getMovieId());

        seenMovie.setDate(seenMovieDTO.getDate());
        seenMovie.setUser(user);
        seenMovie.setMovie(movie);

        seenMovieJPADAO.save(seenMovie);
    }

    public List<String> getSeenMovieIds(String username) {
        User user = userJPADAO.find(username);
        List<SeenMovie> seenMovieList = seenMovieJPADAO.findByUser(user.getId());
        List<String> movieIdList = new ArrayList<>();

        for (SeenMovie seenMovie : seenMovieList) {
            movieIdList.add(seenMovie.getMovie().getExternalId());
        }

        return movieIdList;
    }

    public List<String> getMostSeenMovieIds() {
        List<String> movieIdList = seenMovieJPADAO.findMostSeenMovies();
        return movieIdList;
    }
}

