package com.sbs20011.spring.boot.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages = {"com.sbs20011.spring.boot.mapper.db1"}, sqlSessionFactoryRef = "db1SqlSessionFactory")
public class Db1MyBatisConfig {
	
	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public DataSourceInitializer db1DataSourceInitializer(@Qualifier("db1DataSource") DataSource datasource) {
		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
		resourceDatabasePopulator.addScript(new ClassPathResource("schema-h21.sql"));
		resourceDatabasePopulator.addScript(new ClassPathResource("data-h21.sql"));

		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(datasource);
		dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
		return dataSourceInitializer;
	}
	
	@Bean(name="db1HikariConfig")
	@ConfigurationProperties(prefix = "spring.db1.datasource.hikari")
	public HikariConfig db1HikariConfig() {
		return new HikariConfig();
	}

	@Bean(name="db1DataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.db1.datasource")
	public DataSource db1DataSource() {
		return new HikariDataSource(db1HikariConfig());
	}

	@Bean(name="db1SqlSessionFactory")
	@Primary
	public SqlSessionFactory sqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		sqlSessionFactory.setMapperLocations(applicationContext.getResources("classpath:/mapper/db1/*.xml"));
		return sqlSessionFactory.getObject();
	}
	
	@Bean(name = "db1SqlSessionTemplate")
	@Primary
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	@Bean("db1TransactionManager")
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(db1DataSource());
	}
}