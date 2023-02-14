package fr.epita.rest.controllers;

import fr.epita.rest.web.data.services.MovieDataService;
import fr.epita.rest.web.messages.MovieDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Inject
    MovieDataService movieDataService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@RequestBody MovieDTO movieDTO) {
        try {
            movieDataService.addMovie(movieDTO);
            return ResponseEntity.created(null).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error while performing operation:\n" + e.getMessage());
        }
    }
}
