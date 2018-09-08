package com.speed.config.dao;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class SessionFactoryConfiguration {
	
	//mapper_path=/mapper/**.xml,mapper映射文件路径
	@Value("${mapper_path}")
	private String mapperPath;
	//实体类的包路径com.speed.entity
	@Value("${entity_package}")
	private String entityPackage;
	//数据源
	@Autowired
	//@Qualifier("dataSource"),intellij IDEA需要
	private DataSource dataSource;

	@Bean("sqlSessionFactory")
	public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
		SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
		//mybatis-config.xml的配置文件路径
		//sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(path));
		PathMatchingResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
		//mapper配置文件路径
		String packageSearchPath=PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX+mapperPath;
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packageSearchPath));
		//设置数据源
		sqlSessionFactoryBean.setDataSource(dataSource);
		//实体类的包路径
		sqlSessionFactoryBean.setTypeAliasesPackage(entityPackage);
		return sqlSessionFactoryBean;
	}

}
