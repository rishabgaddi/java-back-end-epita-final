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
import java.util.Set;

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
    AddressJPADAO addressJPADAO;

    @Inject
    @Named("movie-jpadao")
    MovieJPADAO movieJPADAO;

    @Inject
    @Named("contact-jpadao")
    ContactJPADAO contactJPADAO;

    @Inject
    @Named("user-jpadao")
    UserJPADAO userJPADAO;

    @Inject
    @Named("seenmovie-jpadao")
    SeenMovieJPADAO seenMovieJPADAO;

    @AfterEach
    public void cleanup() throws SQLException {
        dataSource.getConnection().prepareStatement("DELETE FROM SEEN_MOVIES").execute();
        dataSource.getConnection().prepareStatement("DELETE FROM ADDRESSES").execute();
        dataSource.getConnection().prepareStatement("DELETE FROM USER_ROLE").execute();
        dataSource.getConnection().prepareStatement("DELETE FROM USERS").execute();
        dataSource.getConnection().prepareStatement("DELETE FROM ROLES").execute();
        dataSource.getConnection().prepareStatement("DELETE FROM CONTACTS").execute();
        dataSource.getConnection().prepareStatement("DELETE FROM MOVIES").execute();
    }

    @Test
    public void testSave() throws SQLException {
        // Given

        // Role
        Role role = new Role();
        role.setName("admin");

        // Address
        Address address = new Address();
        address.setCity("Paris");
        address.setCountry("France");
        address.setStreet("Rue de la Paix");
        address.setNumber("75000");
        address.setArea("75");

        // Movie
        Movie movie = new Movie();
        movie.setTitle("The Matrix");
        movie.setAdded(new Date(2020, 10, 10));
        movie.setExternalId("123456");

        // Contact
        Contact contact = new Contact();
        contact.setName("Rishab Gaddi");
        contact.setEmail("rishab@rishab.com");
        contact.setGender("male");
        contact.setBirthDate(new Date(1998, 10, 10));
        contact.setAddresses(Set.of(address));

        // User
        User user = new User();
        user.setUsername("rishabgaddi");
        user.setRoles(Set.of(role));
        user.setContact(contact);

        // SeenMovie
        SeenMovie seenMovie = new SeenMovie();
        seenMovie.setDate(new Date(2020, 10, 10));
        seenMovie.setMovie(movie);
        seenMovie.setUser(user);

        // When
        roleJPADAO.save(role);
        addressJPADAO.save(address);
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
