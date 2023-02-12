package fr.epita.rest.controllers;

import fr.epita.rest.web.data.services.UserDataService;
import fr.epita.rest.web.messages.UserDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("/users")
public class UserController {
    @Inject
    UserDataService userDataService;

    @GetMapping(path = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> get(@RequestParam("email") String email) {
        try {
            UserDTO userDTO = userDataService.getUserDetails(email);
            return ResponseEntity.ok(userDTO);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
