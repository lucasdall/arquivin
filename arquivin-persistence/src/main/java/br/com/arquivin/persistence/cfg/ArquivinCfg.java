package br.com.arquivin.persistence.cfg;

import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.arquivin.persistence.util.EnvUtil;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "br.com.arquivin.persistence" })
@EnableJpaRepositories(entityManagerFactoryRef = "arquivinEntityManagerFactory", basePackages = { "br.com.arquivin.persistence.repo" }, transactionManagerRef = "arquivinTransactionManager")
@PropertySources({@PropertySource(value = "classpath:/persistence/dbinfo_${arquivin.env}.properties")})
public class ArquivinCfg {

	
	@Autowired
	private ConfigurableEnvironment env;

	@Bean(name = "arquivinDS", destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(env.getProperty("db.driver"));
		ds.setUrl(env.getProperty("db.url"));
		ds.setUsername(env.getProperty("db.user"));
		ds.setPassword(env.getProperty("db.password"));
		ds.setValidationQuery(env.getProperty("db.pool.validationQuery"));
		ds.setMaxTotal(Integer.valueOf(env.getProperty("db.pool.maxActive")));
		ds.setMaxIdle(Integer.valueOf(env.getProperty("db.pool.maxIdle")));
		ds.setMaxWaitMillis(Long.valueOf(env.getProperty("db.pool.maxWait")));
		ds.setRemoveAbandonedOnBorrow(Boolean.valueOf(env.getProperty("db.pool.removeAbandonedOnBorrow")));
		ds.setRemoveAbandonedOnMaintenance(Boolean.valueOf(env.getProperty("db.pool.removeAbandonedOnMaintenance")));
		ds.setRemoveAbandonedTimeout(Integer.valueOf(env.getProperty("db.pool.removeAbandonedTimeout")));
		ds.setLogAbandoned(Boolean.valueOf(env.getProperty("db.pool.logAbandoned")));
		ds.setTestOnCreate(Boolean.valueOf(env.getProperty("db.pool.testOnCreate")));
		ds.setTestOnBorrow(Boolean.valueOf(env.getProperty("db.pool.testOnBorrow")));
		ds.setDefaultAutoCommit(Boolean.FALSE);
		return ds;
	}

	@Bean(name = "arquivinEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("arquivinDS") DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(dataSource);
		lef.setJpaVendorAdapter(jpaVendorAdapter());
		lef.setPackagesToScan("br.com.arquivin.model");
		Map<String, Object> hibernateProps = EnvUtil.getPropertiesStartingWith(env, "hibernate");
		
		// Setando autocommit para false devido a problemas com LOB
		hibernateProps.put("hibernate.connection.autocommit", "false");
		
		lef.setJpaPropertyMap(hibernateProps);
		return lef;
	}

	@Bean(name = "arquivinJpaVendorAdapter")
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		return hibernateJpaVendorAdapter;
	}

	@Bean(name = "arquivinTransactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("arquivinEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager jpa = new JpaTransactionManager(entityManagerFactory);
		return jpa;
	}

}
