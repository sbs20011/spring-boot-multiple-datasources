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

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages = {"com.sbs20011.spring.boot.mapper.db2"}, sqlSessionFactoryRef = "db2SqlSessionFactory")
public class Db2MyBatisConfig {

	@Autowired
	private ApplicationContext applicationContext;

	@Bean(name="db2HikariConfig")
	@ConfigurationProperties(prefix = "spring.db2.datasource.hikari")
	public HikariConfig db2HikariConfig() {
		return new HikariConfig();
	}

	@Bean(name="db2DataSource")
	@ConfigurationProperties(prefix = "spring.db2.datasource")
	public DataSource db2DataSource() {
		return new HikariDataSource(db2HikariConfig());
	}

	@Bean(name="db2SqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		sqlSessionFactory.setMapperLocations(applicationContext.getResources("classpath:/mapper/db2/*.xml"));
		return sqlSessionFactory.getObject();
	}
	
	@Bean(name = "db2SqlSessionTemplate")
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}