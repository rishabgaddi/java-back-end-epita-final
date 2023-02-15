package fr.epita.rest.controllers;

import fr.epita.rest.web.data.services.SeenMovieDataService;
import fr.epita.rest.web.messages.SeenMovieDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/seenmovies")
public class SeenMovieController {
    private static final Logger LOGGER = LogManager.getLogger(SeenMovieController.class);

    @Inject
    SeenMovieDataService seenMovieDataService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@RequestBody SeenMovieDTO seenMovieDTO) {
        try {
            seenMovieDataService.addSeenMovie(seenMovieDTO);
            return ResponseEntity.created(null).build();
        } catch (Exception e) {
            LOGGER.error("Error while performing POST /seenMovies operation", e);
            return ResponseEntity.internalServerError().body("Error while performing operation:\n" + e.getMessage());
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> get(@RequestParam("username") String username) {
        try {
            List<String> movieIds = seenMovieDataService.getSeenMovieIds(username);
            return ResponseEntity.ok(movieIds);
        } catch (Exception e) {
            LOGGER.error("Error while performing GET /seenMovies?username={} operation", username, e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
