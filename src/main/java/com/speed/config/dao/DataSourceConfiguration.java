package com.speed.config.dao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
//配置mybatis的扫描路径
@MapperScan("com.speed.dao")
public class DataSourceConfiguration {
	
	@Value("${jdbc.driver}")
	private String driverClass;
	@Value("${jdbc.url}")
	private String jdbcUrl;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;

	@Bean("dataSource")
	public DruidDataSource createDataSource() {
		DruidDataSource  dataSource=new DruidDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		//关闭连接后不自动commit
		dataSource.setDefaultAutoCommit(false);
		return dataSource;
	}

}
