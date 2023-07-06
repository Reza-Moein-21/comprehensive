package ir.comprehensive.database.factory;

import com.zaxxer.hikari.HikariDataSource;
import ir.comprehensive.common.factory.BeanType;
import ir.comprehensive.common.factory.ObjectFactory;
import ir.comprehensive.database.provider.config.DatabaseProperties;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.sql.DataSource;

public class JooqFactory implements ObjectFactory<DSLContext> {

    private DataSource dataSource() {
        var properties = new DatabaseProperties();
        var dateSource = new HikariDataSource();
        dateSource.setJdbcUrl(properties.getUrl());
        dateSource.setUsername(properties.getUsername());
        dateSource.setPassword(properties.getPassword());
        dateSource.setDriverClassName(properties.getDriverClassName());
        return dateSource;
    }

    private DSLContext dslContext(DataSource dataSource) {
        return DSL.using(dataSource, SQLDialect.H2);
    }

    @Override
    public DSLContext getBean(BeanType type) {
        return dslContext(dataSource());
    }
}
