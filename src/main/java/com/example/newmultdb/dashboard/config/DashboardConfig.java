package com.example.newmultdb.dashboard.config;

import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "secondEntityManagerFactoryBean" ,
        basePackages = {"com.example.newmultdb.dashboard.repo"},
        transactionManagerRef ="secondTransactionManager"
)
public class DashboardConfig {
    @Autowired
    private Environment environment;

    @Bean(name = "secondDataSource")
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl(environment.getProperty("second.datasource.url"));
        dataSource.setUsername(environment.getProperty("second.datasource.username"));
        dataSource.setDriverClassName(environment.getProperty("second.datasource.driver-class-name"));
        dataSource.setPassword(environment.getProperty("second.datasource.password"));
        return dataSource;
    }

    @Bean(name = "secondEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource());

        bean.setPackagesToScan("com.example.newmultdb.dashboard.entities");
        Map<String,String> props = new HashMap<>();
        props.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
        props.put("hibernate.show_sql","true");
        props.put("hibernate.hbm2ddl.auto","update");
        bean.setJpaPropertyMap(props);

        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(adapter);
        return bean;
    }

    @Bean(name = "secondTransactionManager")
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

        return manager;
    }

}
