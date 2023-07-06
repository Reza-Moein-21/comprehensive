package ir.comprehensive.database.test.service;

import ir.comprehensive.database.provider.mapper.CategoryMapper;
import ir.comprehensive.database.provider.service.CategoryDaoImpl;
import ir.comprehensive.database.service.CategoryDao;
import ir.comprehensive.database.test.utils.DBExtension;
import ir.comprehensive.database.test.utils.Sql;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
@Sql("/schema-init.sql")
@ExtendWith(DBExtension.class)
class CategoryDaoImplTest {

    private final CategoryDao categoryDao;

    CategoryDaoImplTest(DSLContext context, CategoryMapper mapper) {
        this.categoryDao = new CategoryDaoImpl(context, mapper);
    }

    @Test
    void initiationTest() {
        assertThat(categoryDao).isNotNull();
    }
}