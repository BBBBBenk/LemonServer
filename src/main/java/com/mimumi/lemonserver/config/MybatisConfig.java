package com.mimumi.lemonserver.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Mybatis配置类
 */
@Configuration
@MapperScan(basePackages = "com.mimumi.lemonserver.mapper")
public class MybatisConfig {

    @Autowired
    private DataSourceProperties properties;

    private String typeAliasesPackage="com.mimumi.lemonserver.entity";

    private String mapperLocations="classpath*:sqlmap/*Mapper.xml";

    @Bean(name = "dataSource")
    public DataSource DataSource() {
        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setDriverClassName(this.properties.getDriverClassName());
        druidDataSource.setUrl(this.properties.getUrl());
        druidDataSource.setUsername(this.properties.getUsername());
        druidDataSource.setPassword(this.properties.getPassword());
        return druidDataSource;
    }

    @Bean(name = "TransactionManager")
    public DataSourceTransactionManager TransactionManager(DataSource dataSource) {
        DataSourceTransactionManager TransactionManager = new DataSourceTransactionManager();
        TransactionManager.setDataSource(dataSource);
        return TransactionManager;
    }

    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory SqlSessionFactory(DataSource dataSource) throws Exception {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean SqlSessionFactory = new SqlSessionFactoryBean();
        SqlSessionFactory.setDataSource(dataSource);
        try {
            SqlSessionFactory.setTypeAliasesPackage(typeAliasesPackage);

            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(mapperLocations);
            SqlSessionFactory.setMapperLocations(resources);
            return SqlSessionFactory.getObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "SqlSessionTemplate")
    public SqlSessionTemplate dmSqlSessionTemplate(SqlSessionFactory SqlSessionFactory) {
        return new SqlSessionTemplate(SqlSessionFactory);
    }
}
