package fr.epita.rest.controllers;

import fr.epita.rest.web.data.services.AccountDataService;
import fr.epita.rest.web.messages.CreateAccountDTO;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/account")
public class AccountController {
    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(AccountController.class);

    @Inject
    AccountDataService accountDataService;

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountDTO createAccountDTO) {
        try {
            accountDataService.createAccount(createAccountDTO);
            return ResponseEntity.created(null).build();
        } catch (Exception e) {
            LOGGER.error("Error while performing POST /account/create operation", e);
            return ResponseEntity.internalServerError().body("Error while performing operation:\n" + e.getMessage());
        }
    }
}
