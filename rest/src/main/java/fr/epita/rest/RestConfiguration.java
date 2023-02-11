package fr.epita.rest;

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
}
