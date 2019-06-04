package com.spring.security.practice.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.spring.security.practice.service.CustomUserDetails;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.security.practice")
@PropertySource("classpath:db-maria.properties")
@EnableJpaRepositories(basePackages = "com.spring.security.practice.repository", entityManagerFactoryRef = "entityManagerFactory")
@EnableTransactionManagement
public class DemoConfiguration implements WebMvcConfigurer {

	@Autowired
	private Environment env;
	
	@Bean
	public CustomUserDetails getUserDetails() {
		return new CustomUserDetails();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManager() throws PropertyVetoException {
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(getDataSource());
		entityManager.setPackagesToScan("com.spring.security.practice.entity");
		entityManager.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManager.setJpaProperties(getHibernateProperties());
		return entityManager;
	}

//	@Bean
//	public LocalSessionFactoryBean getSessionFactory() throws PropertyVetoException {
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		sessionFactory.setDataSource(getDataSource());
//		sessionFactory.setPackagesToScan("com.security.practice.entity");
//		sessionFactory.setHibernateProperties(getHibernateProperties());
//		return sessionFactory;
//	}
	
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hiberante.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		return properties;
	}
	
//	@Bean
//    public HibernateTransactionManager getTransactionManager() throws PropertyVetoException {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.se(getEntityManager().getObject());
//        return transactionManager;
//    }
	
	@Bean
	public DataSource getDataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(env.getProperty("jdbc.driver"));
		dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		dataSource.setUser(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		
		dataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
		dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
		dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
		dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));
		return dataSource;
		
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

}
