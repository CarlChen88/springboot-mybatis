package com.speed.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
//配置mybatis的扫描路径
@MapperScan(value="com.speed.dao",sqlSessionTemplateRef="sqlSessionTemplate")
public class DataSourceConfiguration {
	
	@Value("${jdbc.driver}")
	private String driverClass;
	@Value("${jdbc.url}")
	private String jdbcUrl;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;
	//mapper_path=/mapper/**.xml,mapper映射文件路径
	@Value("${mapper_path}")
	private String mapperPath;
	//实体类的包路径com.speed.entity
	@Value("${entity_package}")
	private String entityPackage;

	@Bean("dataSource")
	@Primary
	public DataSource createDataSource() {
		DruidDataSource  dataSource=new DruidDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		//关闭连接后不自动commit
		//dataSource.setDefaultAutoCommit(false);
		return dataSource;
	}
	
	@Bean("SqlSessionFactory")
	@Primary
	public SqlSessionFactory setSqlSessionFactory(@Qualifier("dataSource") DataSource datasource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setTypeAliasesPackage("entityPackage");
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX+mapperPath));
		sqlSessionFactoryBean.setDataSource(datasource);
		return sqlSessionFactoryBean.getObject();
	}
	@Bean("sqlSessionTemplate")
	@Primary
	public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
