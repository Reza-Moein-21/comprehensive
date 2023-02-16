package ir.comprehensive.database.test.service;

import ir.comprehensive.database.provider.config.JooqConfig;
import ir.comprehensive.database.provider.mapper.ProductMapperImpl;
import ir.comprehensive.database.provider.service.ProductDaoImpl;
import ir.comprehensive.database.service.ProductDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@Sql(scripts = {"classpath:schema-drop.sql", "classpath:schema-create.sql", "classpath:schema-init.sql"})
@SpringJUnitConfig({ProductDaoImpl.class, ProductMapperImpl.class, JooqConfig.class})
public class ProductDaoImplTest {
    @Autowired
    ProductDao productDao;

    @Test
    void initiationTest() {
        assertThat(productDao).isNotNull();
    }
}
