package fr.epita.rest;

import fr.epita.rest.web.data.services.MovieDataService;
import fr.epita.rest.web.data.services.UserDataService;
import fr.epita.services.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class RestConfiguration {
    @Bean(name = "data-source")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/movies");
        dataSource.setUsername("senorita");
        dataSource.setPassword("admin");
        return dataSource;
    }

    @Bean(name = "session-factory")
    public LocalSessionFactoryBean factoryBean(@Qualifier("data-source") DataSource dataSource) {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);

        String packages = "fr.epita.datamodel";
        sessionFactoryBean.setPackagesToScan(packages);

        Properties properties = new Properties();

        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        sessionFactoryBean.setHibernateProperties(properties);

        return sessionFactoryBean;
    }

    @Bean(name = "address-jpadao")
    public AddressJPADAO getAddressJPADAO(@Qualifier("session-factory") SessionFactory sessionFactory) {
        return new AddressJPADAO(sessionFactory);
    }

    @Bean(name = "contact-jpadao")
    public ContactJPADAO getContactJPADAO(@Qualifier("session-factory") SessionFactory sessionFactory) {
        return new ContactJPADAO(sessionFactory);
    }

    @Bean(name = "movie-jpadao")
    public MovieJPADAO getMovieJPADAO(@Qualifier("session-factory") SessionFactory sessionFactory) {
        return new MovieJPADAO(sessionFactory);
    }

    @Bean(name = "role-jpadao")
    public RoleJPADAO getRoleJPADAO(@Qualifier("session-factory") SessionFactory sessionFactory) {
        return new RoleJPADAO(sessionFactory);
    }

    @Bean(name = "seenmovie-jpadao")
    public SeenMovieJPADAO getSeenMovieJPADAO(@Qualifier("session-factory") SessionFactory sessionFactory) {
        return new SeenMovieJPADAO(sessionFactory);
    }

    @Bean(name = "user-jpadao")
    public UserJPADAO getUserJPADAO(@Qualifier("session-factory") SessionFactory sessionFactory) {
        return new UserJPADAO(sessionFactory);
    }

    @Bean(name = "user-data-service")
    public UserDataService getUserDataService(ContactJPADAO contactJPADAO, UserJPADAO userJPADAO, RoleJPADAO roleJPADAO, @Qualifier("session-factory") SessionFactory sessionFactory) {
        return new UserDataService(contactJPADAO, userJPADAO, roleJPADAO, sessionFactory);
    }

    @Bean(name = "movie-data-service")
    public MovieDataService getMovieDataService(MovieJPADAO movieJPADAO, @Qualifier("session-factory") SessionFactory sessionFactory) {
        return new MovieDataService(movieJPADAO, sessionFactory);
    }
}
