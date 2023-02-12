package fr.epita.tests.jpa;

import fr.epita.datamodel.*;
import fr.epita.services.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JPATestContextConfiguration.class)
public class TestJPADAO {
    @Inject
    @Named("data-source")
    DataSource dataSource;

    @Inject
    @Named("role-jpadao")
    IRoleDAO roleJPADAO;

    @Inject
    @Named("address-jpadao")
    IDAO addressJPADAO;

    @Inject
    @Named("movie-jpadao")
    IDAO movieJPADAO;

    @Inject
    @Named("contact-jpadao")
    IDAO contactJPADAO;

    @Inject
    @Named("user-jpadao")
    IDAO userJPADAO;

    @Inject
    @Named("seenmovie-jpadao")
    IDAO seenMovieJPADAO;

    @AfterEach
    public void cleanup() throws SQLException {
        dataSource.getConnection().prepareStatement("DELETE FROM SEEN_MOVIES").execute();
        dataSource.getConnection().prepareStatement("DELETE FROM USERS").execute();
        dataSource.getConnection().prepareStatement("DELETE FROM ROLES").execute();
        dataSource.getConnection().prepareStatement("DELETE FROM CONTACTS").execute();
        dataSource.getConnection().prepareStatement("DELETE FROM MOVIES").execute();
        dataSource.getConnection().prepareStatement("DELETE FROM ADDRESSES").execute();
    }

    @Test
    public void testSave() throws SQLException {
        // Given

        // Role
        Role role = new Role();
        role.setName("admin");

        // Address
        Address address1 = new Address();
        address1.setCity("Paris");
        address1.setCountry("France");
        address1.setStreet("Rue de la Paix");
        address1.setNumber("75000");
        address1.setArea("75");
        address1.setPrimary(true);

        Address address2 = new Address();
        address2.setCity("Hyderabad");
        address2.setCountry("India");
        address2.setStreet("Esamia Bazar");
        address2.setNumber("50027");
        address2.setArea("27");
        address2.setPrimary(false);

        // Movie
        Movie movie = new Movie();
        movie.setTitle("The Matrix");
        movie.setAdded(new Date(2020, 10, 10));
        movie.setExternalId("123456");

        // Contact
        Contact contact = new Contact();
        contact.setFirstName("Rishab");
        contact.setLastName("Gaddi");
        contact.setEmail("rishab@rishab.com");
        contact.setGender("male");
        contact.setBirthDate(new Date(1998, 10, 10));
        contact.setPrimaryAddress(address1);
        contact.setSecondaryAddress(address2);

        // User
        User user = new User();
        user.setUsername("rishabgaddi");
        user.setRole(role);
        user.setContact(contact);
        contact.setUser(user);

        // SeenMovie
        SeenMovie seenMovie = new SeenMovie();
        seenMovie.setDate(new Date(2020, 10, 10));
        seenMovie.setMovie(movie);
        seenMovie.setUser(user);

        // When
        roleJPADAO.save(role);
        addressJPADAO.save(address1);
        addressJPADAO.save(address2);
        movieJPADAO.save(movie);
        contactJPADAO.save(contact);
        userJPADAO.save(user);
        seenMovieJPADAO.save(seenMovie);

        // Then
        ResultSet resultSet = dataSource.getConnection().
                prepareStatement("SELECT * FROM ROLES WHERE name = 'admin'")
                .executeQuery();
        resultSet.next();
        String name = resultSet.getString("name");
        Assertions.assertEquals("admin", name);

        resultSet = dataSource.getConnection().
                prepareStatement("SELECT * FROM USERS WHERE username = 'rishabgaddi'")
                .executeQuery();
        resultSet.next();
        String username = resultSet.getString("username");
        Assertions.assertEquals("rishabgaddi", username);
    }
}
