package com.aldiichsantkj3.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@RefreshScope
@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.postgre.url}")
    String jdbcUrl;

    @Value("${spring.datasource.postgre.username}")
    String username;

    @Value("${spring.datasource.postgre.password}")
    String password;

    @Value("${spring.datasource.postgre.driver-class-name}")
    String postgreJdbcDriver;


    @RefreshScope
    @Bean
    public DataSource getDataSource() {
        HikariConfig hConfig = new HikariConfig();
        hConfig.setJdbcUrl(jdbcUrl); //prod
        hConfig.setUsername(username);
        hConfig.setPassword(password);
        hConfig.setDriverClassName(postgreJdbcDriver);

        return new HikariDataSource(hConfig);
    }

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        org.apache.ibatis.session.Configuration ibatisConfiguration = new org.apache.ibatis.session.Configuration();
        ibatisConfiguration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(ibatisConfiguration);

        sqlSessionFactoryBean.setDataSource(getDataSource());
        return sqlSessionFactoryBean.getObject();
    }

}