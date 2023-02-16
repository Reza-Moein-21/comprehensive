package ir.comprehensive.database.test.service;

import ir.comprehensive.database.provider.config.JooqConfig;
import ir.comprehensive.database.provider.mapper.WarehouseCategoryMapperImpl;
import ir.comprehensive.database.provider.service.WarehouseCategoryDaoImpl;
import ir.comprehensive.database.service.WarehouseCategoryDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@Sql(scripts = {"classpath:schema-drop.sql", "classpath:schema-create.sql", "classpath:schema-init.sql"})
@SpringJUnitConfig({WarehouseCategoryDaoImpl.class, WarehouseCategoryMapperImpl.class, JooqConfig.class})
public class WarehouseCategoryDaoImplTest {
    @Autowired
    WarehouseCategoryDao warehouseCategoryDao;

    @Test
    void initiationTest() {
        assertThat(warehouseCategoryDao).isNotNull();
    }
}
