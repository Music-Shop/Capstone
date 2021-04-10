package com.hcl.MusicMelody;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Properties;

import javax.sql.DataSource;
 
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
@EnableJpaRepositories
public class MusicMelodyApplication {

	@Autowired
    private Environment env;
	
	public static void main(String[] args) {
		SpringApplication.run(MusicMelodyApplication.class, args);
	}

	
	 @Bean
	    public ServletWebServerFactory servletContainer() {
	        // Enable SSL Trafic
	        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
	            @Override
	            protected void postProcessContext(Context context) {
	                SecurityConstraint securityConstraint = new SecurityConstraint();
	                securityConstraint.setUserConstraint("CONFIDENTIAL");
	                SecurityCollection collection = new SecurityCollection();
	                collection.addPattern("/*");
	                securityConstraint.addCollection(collection);
	                context.addConstraint(securityConstraint);
	            }
	        };

	        // Add HTTP to HTTPS redirect
	        tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());

	        return tomcat;
	    }

	    /*
	    We need to redirect from HTTP to HTTPS. Without SSL, this application used
	    port 8082. With SSL it will use port 8443. So, any request for 8082 needs to be
	    redirected to HTTPS on 8443.
	     */
	    private Connector httpToHttpsRedirectConnector() {
	        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
	        connector.setScheme("http");
	        connector.setPort(8082);
	        connector.setSecure(false);
	        connector.setRedirectPort(8443);
	        return connector;
	    }
	    
	    
	    
	    
	    
//	    @Bean(name = "dataSource")
//	    public DataSource getDataSource() {
//	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//	 
//	        // See: application.properties
//	        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
//	        dataSource.setUrl(env.getProperty("spring.datasource.url"));
//	        dataSource.setUsername(env.getProperty("spring.datasource.username"));
//	        dataSource.setPassword(env.getProperty("spring.datasource.password"));
//	 
//	        System.out.println("## getDataSource: " + dataSource);
//	 
//	        return dataSource;
//	    }
	 
	    @Autowired
	    @Bean(name = "sessionFactory")
	    public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
	        Properties properties = new Properties();
	 
	        // See: application.properties  
	        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
	        properties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
	        properties.put("current_session_context_class", //
	                env.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
	 
	        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
	 
	        // Package contain entity classes
	        factoryBean.setPackagesToScan(new String[] { "" });
	        factoryBean.setDataSource(dataSource);
	        factoryBean.setHibernateProperties(properties);
	        factoryBean.afterPropertiesSet();
	        //
	        SessionFactory sf = factoryBean.getObject();
	        System.out.println("## getSessionFactory: " + sf);
	        return sf;
	    }
	 
	    @Autowired
	    @Bean(name = "transactionManager")
	    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
	        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
	 
	        return transactionManager;
	    }
}
