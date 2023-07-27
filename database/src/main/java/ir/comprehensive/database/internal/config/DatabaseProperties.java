package ir.comprehensive.database.internal.config;

import ir.comprehensive.database.exception.DatabasePropertiesNotFoundException;

import java.io.IOException;
import java.util.Properties;

public final class DatabaseProperties {
    private final String url;
    private final String username;
    private final String password;
    private final String driverClassName;

    public DatabaseProperties() {
        Properties p = new Properties();
        try {
            p.load(getClass().getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            throw new DatabasePropertiesNotFoundException(e);
        }

        this.url = p.getProperty("datasource.url");
        this.username = p.getProperty("datasource.username");
        this.password = p.getProperty("datasource.password");
        this.driverClassName = p.getProperty("datasource.driverClassName");
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }
}
