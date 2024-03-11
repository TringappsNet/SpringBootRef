package com.springboot.springbootref.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "staffEntityManagerFactory",
        basePackages 	 = {"com.springboot.springbootref.staff"},
        transactionManagerRef = "staffTransactionManager"
)
public class StaffDataSourceConfig {
    @Autowired
    Environment env;


    @Bean(name= "staffDataSource")
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(env.getProperty("spring.datasource.mssql.url"));
        ds.setUsername(env.getProperty("spring.datasource.mssql.username"));
        ds.setPassword(env.getProperty("spring.datasource.mssql.password"));
        ds.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.datasource.mssql.driver-class-name")));
        return ds;
    }


    @Bean(name= "staffEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManager() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource());
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(adapter);
        HashMap<String,Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        bean.setJpaPropertyMap(properties);
        bean.setPackagesToScan("com.springboot.springbootref.staff");
        return bean;

    }

    @Bean("staffTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("staffEntityManagerFactory") EntityManagerFactory entityManagerFactory ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
