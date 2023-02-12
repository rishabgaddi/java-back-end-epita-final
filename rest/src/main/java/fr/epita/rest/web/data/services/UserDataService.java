package fr.epita.rest.web.data.services;

import fr.epita.datamodel.Contact;
import fr.epita.datamodel.Role;
import fr.epita.datamodel.User;
import fr.epita.rest.web.messages.UserDTO;
import fr.epita.services.ContactJPADAO;
import fr.epita.services.RoleJPADAO;
import fr.epita.services.UserJPADAO;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;

public class UserDataService {
    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(UserDataService.class);
    private final ContactJPADAO contactJPADAO;
    private final UserJPADAO userJPADAO;
    private final RoleJPADAO roleJPADAO;
    private final SessionFactory sessionFactory;

    public UserDataService(ContactJPADAO contactJPADAO, UserJPADAO userJPADAO, RoleJPADAO roleJPADAO, SessionFactory sessionFactory) {
        this.contactJPADAO = contactJPADAO;
        this.userJPADAO = userJPADAO;
        this.roleJPADAO = roleJPADAO;
        this.sessionFactory = sessionFactory;
    }

    public UserDTO getUserDetails(String email) {
        try {
            Contact contact = contactJPADAO.findByEmail(email);
            User user = contact.getUser();
            Role role = user.getRole();

            UserDTO userDTO = new UserDTO();
            userDTO.setFirstName(contact.getFirstName());
            userDTO.setLastName(contact.getLastName());
            userDTO.setEmail(contact.getEmail());
            userDTO.setUsername(user.getUsername());
            userDTO.setType(role.getName());

            return userDTO;
        } catch (Exception e) {
            LOGGER.error("Error while getting user details", e);
            return null;
        }
    }
}
