package com.denisgl.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.denisgl.dtoimpl", "com.denisgl.daoimpl", "com.denisgl.serviceimpl"})
@EnableTransactionManagement
public class HibernateConfig {

    public static final String DATABASE_URL = "jdbc:postgresql://127.0.0.1:5432/shop";
    public static final String DATABASE_DRIVER = "org.postgresql.Driver";
    public static final String DATABASE_DIALECT = "org.hibernate.dialect.PostgreSQL95Dialect";
    public static final String DATABASE_USERNAME = "postgres";
    public static final String DATABASE_PASSWORD = "123456";

    @Bean
    DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DATABASE_DRIVER);
        dataSource.setUrl(DATABASE_URL);
        dataSource.setUsername(DATABASE_USERNAME);
        dataSource.setPassword(DATABASE_PASSWORD);

        return dataSource;
    }

    @Bean
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
        builder.addProperties(getHibernateProperties());
        builder.scanPackages("com.denisgl.dtoimpl");

        return builder.buildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

        return transactionManager;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", DATABASE_DIALECT);
        properties.put("hibernate.show_sql", "false");
        properties.put("hibernate.format_sql", "false");
//        properties.put("hibernate.hbm2ddl.auto", "update");

        return properties;
    }
}
