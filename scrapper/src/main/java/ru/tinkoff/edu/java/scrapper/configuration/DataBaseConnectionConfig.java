package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.tinkoff.edu.java.scrapper.configuration.configs.DataBaseConfig;

import javax.sql.DataSource;

@Configuration
public class DataBaseConnectionConfig {

    @Bean
    DataSource dataSource(ApplicationConfig applicationConfig) {
        DataBaseConfig dataBaseConfig = applicationConfig.dataBase();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataBaseConfig.driverClassName());
        dataSource.setUrl(dataBaseConfig.url());
        dataSource.setUsername(dataBaseConfig.userName());
        dataSource.setPassword(dataBaseConfig.password());
        return dataSource;
    }


    @Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
