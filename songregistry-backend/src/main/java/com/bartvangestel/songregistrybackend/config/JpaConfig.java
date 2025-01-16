package com.bartvangestel.songregistrybackend.config;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableJpaRepositories
public class JpaConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factory = builder
                .dataSource(dataSource)
                .packages("com.bartvangestel.songregistrybackend")  // Your package with entities
                .persistenceUnit("songregistry")
                .build();
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setJpaPropertyMap(Map.of("hibernate.dialect", "org.hibernate.dialect.MySQLDialect"));
        return factory;
    }
}
