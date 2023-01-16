package fr.epita.tests.jpa;

import fr.epita.datamodel.Role;
import fr.epita.services.RoleJPADAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JPATestContextConfiguration.class)
public class TestRoleJPA {
    @Inject
    @Named("data-source")
    DataSource dataSource;

    @Inject
    @Named("role-jpadao")
    RoleJPADAO roleJPADAO;

    @AfterEach
    public void cleanup() throws SQLException {
        dataSource.getConnection().prepareStatement("DELETE FROM ROLES").execute();
    }

    @Test
    public void testSave() throws SQLException {
        // Given
        Role role = new Role();
        role.setName("admin");

        // When
        roleJPADAO.save(role);

        // Then
        ResultSet resultSet = dataSource.getConnection().
                prepareStatement("SELECT * FROM ROLES WHERE name = 'admin'")
                .executeQuery();
        resultSet.next();
        String name = resultSet.getString("name");
        Assertions.assertEquals("admin", name);
    }

    @Test
    public void testUpdate() throws SQLException {
        // Given
        Role role = new Role();
        role.setName("admin");
        roleJPADAO.save(role);
        role.setName("non-admin");

        // When
        roleJPADAO.update(role);

        // Then
        ResultSet resultSet = dataSource.getConnection().
                prepareStatement("SELECT * FROM ROLES WHERE name = 'non-admin'")
                .executeQuery();
        resultSet.next();
        String name = resultSet.getString("name");
        Assertions.assertEquals("non-admin", name);
    }

    @Test
    public void testFindById() throws SQLException {
        // Given
        Role role = new Role();
        role.setName("admin");
        roleJPADAO.save(role);

        // When
        Role foundRole = roleJPADAO.findById(role.getId());

        // Then
        Assertions.assertEquals("admin", foundRole.getName());
    }

    @Test
    public void testFindByName() throws SQLException {
        // Given
        Role role = new Role();
        role.setName("admin");
        roleJPADAO.save(role);

        // When
        Role foundRole = roleJPADAO.findByName(role.getName());

        // Then
        Assertions.assertEquals("admin", foundRole.getName());
    }

    @Test
    public void testFindAll() throws SQLException {
        // Given
        Role role = new Role();
        role.setName("admin");
        roleJPADAO.save(role);
        Role role2 = new Role();
        role2.setName("non-admin");
        roleJPADAO.save(role2);

        // When
        List<Role> foundRoles = roleJPADAO.findAll();

        // Then
        Assertions.assertEquals(2, foundRoles.size());
    }

    @Test
    public void testDelete() throws SQLException {
        // Given
        Role role = new Role();
        role.setName("admin");
        roleJPADAO.save(role);

        // When
        roleJPADAO.delete(role);

        // Then
        ResultSet resultSet = dataSource.getConnection().
                prepareStatement("SELECT * FROM ROLES WHERE name = 'admin'")
                .executeQuery();
        Assertions.assertFalse(resultSet.next());
    }
}
