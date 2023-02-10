package ir.comprehensive.database.test.service;

import ir.comprehensive.database.provider.config.JooqConfig;
import ir.comprehensive.database.provider.mapper.CategoryMapperImpl;
import ir.comprehensive.database.provider.service.CategoryDaoImpl;
import ir.comprehensive.database.service.CategoryDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;


@Sql(scripts = {"classpath:schema-drop.sql", "classpath:schema-create.sql", "classpath:schema-init.sql"})
@SpringJUnitConfig({CategoryDaoImpl.class, CategoryMapperImpl.class, JooqConfig.class})
class CategoryDaoImplTest {

    @Autowired
    CategoryDao categoryDao;

    @Test
    void initiationTest() {
        assertThat(categoryDao).isNotNull();
    }
}