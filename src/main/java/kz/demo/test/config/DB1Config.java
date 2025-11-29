package kz.demo.test.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryRef1",
        basePackages = "kz.demo.test.repository",
        transactionManagerRef = "transactionManagerRef1"
)
public class DB1Config {

    @Primary
    @Bean(name = "dataSource1")
    @ConfigurationProperties("spring.db1.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "entityManagerFactoryRef1")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            EntityManagerFactoryBuilder builder, //package org.springframework.boot.orm.jpa;
            @Qualifier("dataSource1")DataSource dataSource){

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hb2ddl-auto", "update");

        return builder
                .dataSource(dataSource)
                .properties(properties)
                .packages("kz.demo.test.model")
                .persistenceUnit("db1")
                .build();
    }

    @Bean(name = "transactionManagerRef1")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactoryRef1") EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }
}
