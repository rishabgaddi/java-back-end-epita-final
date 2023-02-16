package fr.epita.rest.controllers;

import fr.epita.rest.web.data.services.RoleDataService;
import fr.epita.rest.web.messages.RoleDTO;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(RoleController.class);
    @Inject
    RoleDataService roleDataService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RoleDTO>> get() {
        try {
            List<RoleDTO> roleDTOList = roleDataService.getRoles();
            return ResponseEntity.ok(roleDTOList);
        } catch (Exception e) {
            LOGGER.error("Error while performing GET /roles operation", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
