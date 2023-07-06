package ir.comprehensive.database.test.service;

import ir.comprehensive.database.provider.mapper.WarehouseCategoryMapper;
import ir.comprehensive.database.provider.service.WarehouseCategoryDaoImpl;
import ir.comprehensive.database.service.WarehouseCategoryDao;
import ir.comprehensive.database.test.utils.DBExtension;
import ir.comprehensive.database.test.utils.Sql;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
@Sql("/schema-init.sql")
@ExtendWith(DBExtension.class)
public class WarehouseCategoryDaoImplTest {

    private final WarehouseCategoryDao warehouseCategoryDao;

    public WarehouseCategoryDaoImplTest(DSLContext context, WarehouseCategoryMapper mapper) {
        this.warehouseCategoryDao = new WarehouseCategoryDaoImpl(context, mapper);
    }

    @Test
    void initiationTest() {
        assertThat(warehouseCategoryDao).isNotNull();
    }
}
