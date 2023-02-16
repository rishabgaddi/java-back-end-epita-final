package fr.epita.rest.web.data.services;

import fr.epita.datamodel.Address;
import fr.epita.datamodel.Contact;
import fr.epita.datamodel.Role;
import fr.epita.datamodel.User;
import fr.epita.rest.web.messages.CreateAccountDTO;
import fr.epita.services.AddressJPADAO;
import fr.epita.services.ContactJPADAO;
import fr.epita.services.RoleJPADAO;
import fr.epita.services.UserJPADAO;
import org.hibernate.SessionFactory;

public class AccountDataService {
    private final RoleJPADAO roleJPADAO;
    private final AddressJPADAO addressJPADAO;
    private final ContactJPADAO contactJPADAO;
    private final UserJPADAO userJPADAO;
    private final SessionFactory sessionFactory;

    public AccountDataService(RoleJPADAO roleJPADAO, AddressJPADAO addressJPADAO, ContactJPADAO contactJPADAO, UserJPADAO userJPADAO, SessionFactory sessionFactory) {
        this.roleJPADAO = roleJPADAO;
        this.addressJPADAO = addressJPADAO;
        this.contactJPADAO = contactJPADAO;
        this.userJPADAO = userJPADAO;
        this.sessionFactory = sessionFactory;
    }

    public void createAccount(CreateAccountDTO createAccountDTO) {
        Role role = roleJPADAO.findByName(createAccountDTO.getRole());

        Address primaryAddress = new Address();
        primaryAddress.setCity(createAccountDTO.getPrimaryAddress().getCity());
        primaryAddress.setCountry(createAccountDTO.getPrimaryAddress().getCountry());
        primaryAddress.setArea(createAccountDTO.getPrimaryAddress().getArea());
        primaryAddress.setNumber(createAccountDTO.getPrimaryAddress().getNumber());
        primaryAddress.setStreet(createAccountDTO.getPrimaryAddress().getStreet());
        primaryAddress.setPrimary(true);
        addressJPADAO.save(primaryAddress);

        Address secondaryAddress = new Address();
        secondaryAddress.setCity(createAccountDTO.getSecondaryAddress().getCity());
        secondaryAddress.setCountry(createAccountDTO.getSecondaryAddress().getCountry());
        secondaryAddress.setArea(createAccountDTO.getSecondaryAddress().getArea());
        secondaryAddress.setNumber(createAccountDTO.getSecondaryAddress().getNumber());
        secondaryAddress.setStreet(createAccountDTO.getSecondaryAddress().getStreet());
        addressJPADAO.save(secondaryAddress);

        Contact contact = new Contact();
        contact.setFirstName(createAccountDTO.getFirstName());
        contact.setLastName(createAccountDTO.getLastName());
        contact.setBirthDate(createAccountDTO.getBirthDate());
        contact.setEmail(createAccountDTO.getEmail());
        contact.setGender(createAccountDTO.getGender());
        contact.setPrimaryAddress(primaryAddress);
        contact.setSecondaryAddress(secondaryAddress);
        contactJPADAO.save(contact);

        User user = new User();
        user.setUsername(createAccountDTO.getUsername());
        user.setRole(role);
        user.setContact(contact);
        contact.setUser(user);
        userJPADAO.save(user);
    }
}
