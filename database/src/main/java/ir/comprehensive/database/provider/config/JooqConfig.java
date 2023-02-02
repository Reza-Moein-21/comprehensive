package ir.comprehensive.database.provider.config;

import com.zaxxer.hikari.HikariDataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class JooqConfig {

    @Value("${spring.datasource.url}")
    public String url;

    @Value("${spring.datasource.username}")
    public String username;

    @Value("${spring.datasource.password}")
    public String password;

    @Value("${spring.datasource.driverClassName}")
    public String driverClassName;

    @Bean
    public DataSource dataSource() {
        var dateSource = new HikariDataSource();
        dateSource.setJdbcUrl(url);
        dateSource.setUsername(username);
        dateSource.setPassword(password);
        dateSource.setDriverClassName(driverClassName);
        return dateSource;
    }

    @Bean
    public DSLContext dslContext(DataSource dataSource) {
        return DSL.using(dataSource, SQLDialect.H2);
    }
}
