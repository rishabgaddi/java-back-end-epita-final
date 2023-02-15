package fr.epita.rest.web.data.services;

import fr.epita.datamodel.Contact;
import fr.epita.datamodel.Role;
import fr.epita.datamodel.User;
import fr.epita.rest.web.messages.UserDTO;
import fr.epita.services.UserJPADAO;
import org.hibernate.SessionFactory;

public class UserDataService {
    private final UserJPADAO userJPADAO;
    private final SessionFactory sessionFactory;

    public UserDataService(UserJPADAO userJPADAO, SessionFactory sessionFactory) {
        this.userJPADAO = userJPADAO;
        this.sessionFactory = sessionFactory;
    }

    public UserDTO getUserDetails(String username) {
        User user = userJPADAO.find(username);
        Contact contact = user.getContact();
        Role role = user.getRole();

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(contact.getEmail());
        userDTO.setFirstName(contact.getFirstName());
        userDTO.setLastName(contact.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setType(role.getName());

        return userDTO;
    }
}
