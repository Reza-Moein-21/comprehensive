package ir.comprehensive.database;


import ir.comprehensive.database.provider.config.JooqConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan
@Import(JooqConfig.class)
public class DatabaseModule {
}
