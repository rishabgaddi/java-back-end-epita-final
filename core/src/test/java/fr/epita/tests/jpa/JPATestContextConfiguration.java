package fr.epita.tests.jpa;

import fr.epita.services.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class JPATestContextConfiguration {
    @Bean(name = "data-source")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        dataSource.setPassword("test");
        dataSource.setUsername("sa");

//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/movies");
//        dataSource.setUsername("senorita");
//        dataSource.setPassword("admin");
        return dataSource;
    }

    @Bean(name = "session-factory")
    public LocalSessionFactoryBean factoryBean(@Qualifier("data-source") DataSource dataSource) {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);

        String packages = "fr.epita.datamodel";
        sessionFactoryBean.setPackagesToScan(packages);

        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.hbm2ddl.auto", "create");

//        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//        properties.put("hibernate.hbm2ddl.auto", "update");
        sessionFactoryBean.setHibernateProperties(properties);

        return sessionFactoryBean;
    }

    @Bean(name = "transaction-manager")
    public HibernateTransactionManager transactionManager(@Qualifier("session-factory") SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
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
}
