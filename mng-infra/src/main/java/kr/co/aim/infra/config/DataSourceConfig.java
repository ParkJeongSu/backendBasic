package kr.co.aim.infra.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "mssqlDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    @Profile({"pex","tex","dispatcher","scheduler"})
    public DataSource mssqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "db2DataSource")
    @ConfigurationProperties(prefix = "spring.db2-datasource")
    @Profile({"scheduler"})
    public DataSource db2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "db2JdbcTemplate")
    @Profile({"scheduler"})
    public JdbcTemplate db2JdbcTemplate(@Qualifier("db2DataSource")DataSource db2DataSource) {
        return new JdbcTemplate(db2DataSource);
    }
}