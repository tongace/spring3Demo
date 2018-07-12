package com.dxc.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan
@EnableTransactionManagement
@PropertySource(value = { "classpath:data-access.properties" })
@ImportResource(value = { "classpath:app-jdbc-datasource.xml", "classpath:batch-jdbc-datasource.xml" })
@MapperScan("com.dxc.application.mybatis.mappers")
public class DatabaseConfig {
	@Autowired
	private DataSource appDataSource;
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(appDataSource);
		SqlSessionFactory sessionFactory = sessionFactoryBean.getObject();
		sessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
		return sessionFactory;
	}
}
