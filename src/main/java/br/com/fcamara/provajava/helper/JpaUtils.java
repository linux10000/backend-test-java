package br.com.fcamara.provajava.helper;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

public class JpaUtils {

	@Bean()
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrincipal() throws Exception {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setPersistenceUnitName("unitPrincipal");
		em.setDataSource(dataSourcePrincipal());
		em.setPackagesToScan(new String[] { "br.com.fcamara.provajava.pojo" });
		
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		//em.setJpaProperties(additionalProperties());
		

	
		return em;
	}
	
//	@Bean
//	public HikariDataSource dataSourcePrincipal() throws Exception {
//		
//		HikariDataSource dataSource = new HikariDataSource();
//		dataSource.setDriverClassName("org.postgresql.Driver");
//		dataSource.setJdbcUrl(pgUrl);
//		dataSource.setUsername(pgUser);
//		dataSource.setPassword(pgPassword);
//		
//		if ( pgSslmode != null && !pgSslmode.trim().isEmpty() ) {
//			dataSource.addDataSourceProperty("sslmode", pgSslmode);
//			dataSource.addDataSourceProperty("sslcert", pgSslcert);
//			dataSource.addDataSourceProperty("sslkey", pgSslkey);
//			dataSource.addDataSourceProperty("sslpassword", pgSslpassword);
//			dataSource.addDataSourceProperty("sslrootcert", pgSslrootcert);
//			dataSource.setMaximumPoolSize(pgMaxpoolsize); //300 eh o maximo do maximo recomendado para postgresql
//		}
//		
//		
//		return dataSource;
//	}
	
	@Bean
	public DataSource dataSourcePrincipal() {
		
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder
			.setType(EmbeddedDatabaseType.H2)
//			.addScript("create-db.sql")
//			.addScript("db/sql/insert-data.sql")
			.build();
		return db;
	}

	
	@Bean()
	public PlatformTransactionManager transactionManagerPrincipal(
			EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		
		return transactionManager;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
//	private Properties additionalProperties() {
//
//		Properties properties = new Properties();
//		properties.setProperty("hibernate.hbm2ddl.auto", "validate");
//		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
//		properties.setProperty("format_sql", "true");
//		properties.setProperty("hibernate.show_sql", "true");
//		properties.setProperty("hibernate.jdbc.lob.non_contextual_creation", "true");
////		properties.setProperty("javax.persistence.validation.mode", "ddl");
////		properties.setProperty("spring.jpa.hibernate.ddl-auto", "validate");
//		
//		properties.setProperty("hibernate.order_inserts", "true");
//		properties.setProperty("hibernate.order_updates", "true");
//		properties.setProperty("hibernate.jdbc.batch_size", "50");
////		properties.setProperty("log4j.logger.org.hibernate.type", "trace");
////		properties.setProperty("hibernate.show_sql", "true");
////		properties.setProperty("hibernate.format_sql", "true");
////		properties.setProperty("hibernate.use_sql_comments", "true");
////		properties.setProperty("logging.level.org.hibernate.type.descriptor.sql", "trace");
////		properties.setProperty("hibernate.c3p0.acquire_increment", "1");
////		properties.setProperty("hibernate.c3p0.idle_test_period", "5000");
////		properties.setProperty("hibernate.c3p0.max_size", "500");
////		properties.setProperty("hibernate.c3p0.max_statements", "0");
////		properties.setProperty("hibernate.c3p0.min_size", "1");
////		properties.setProperty("hibernate.c3p0.timeout", "600");
////		properties.setProperty("hibernate.c3p0.validate", "true");
////		properties.setProperty("hibernate.c3p0.acquireRetryAttempts", "30");
////		properties.setProperty("hibernate.c3p0.checkoutTimeout", "30000");
////		properties.setProperty("hibernate.c3p0.preferredTestQuery", "select 1 from rdb$database");
////		//properties.setProperty("hibernate.c3p0.testConnectionOnCheckout", "true");
////		properties.setProperty("hibernate.c3p0.testConnectionOnCheckin", "true");
//		
////		properties.setProperty("annotatedClasses", "br.com.hcf.erp.pojo.Banco");
//		return properties;
//	}
}
