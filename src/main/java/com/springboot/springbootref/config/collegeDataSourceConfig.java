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
        entityManagerFactoryRef = "collegeEntityManagerFactory",
        basePackages     = {"com.springboot.springbootref.colleges"},
        transactionManagerRef = "collegeTransactionManager"
)
public class collegeDataSourceConfig {
    @Autowired
    private Environment env;
    @Bean(name= "oracleDataSource")
    public DataSource oracledataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(env.getProperty("spring.datasource.oracle.url"));
        ds.setUsername(env.getProperty("spring.datasource.oracle.username"));
        ds.setPassword(env.getProperty("spring.datasource.oracle.password"));
        ds.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.datasource.oracle.driver-class-name")));
        return ds;
    }

    @Bean(name= "collegeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean oracleentityManager() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(oracledataSource());
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(adapter);
        HashMap<String,Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
        bean.setJpaPropertyMap(properties);
        bean.setPackagesToScan("com.springboot.springbootref.colleges");
        return bean;
    }

    @Bean("collegeTransactionManager")
    public PlatformTransactionManager oracleTransactionManager(@Qualifier("collegeEntityManagerFactory") EntityManagerFactory entityManagerFactory ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
