package ir.comprehensive.database.test.dao;

import ir.comprehensive.database.internal.mapper.WarehouseCategoryMapper;
import ir.comprehensive.database.internal.dao.impl.WarehouseCategoryDaoImpl;
import ir.comprehensive.database.dao.WarehouseCategoryDao;
import ir.comprehensive.database.test.utils.DBExtension;
import ir.comprehensive.database.test.utils.Sql;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
@Sql("/schema-init.sql")
@ExtendWith(DBExtension.class)
public class WarehouseCompanyDaoImplTest {

    private final WarehouseCategoryDao warehouseCategoryDao;

    public WarehouseCompanyDaoImplTest(DSLContext context, WarehouseCategoryMapper mapper) {
        this.warehouseCategoryDao = new WarehouseCategoryDaoImpl(context, mapper);
    }

    @Test
    void initiationTest() {
        assertThat(warehouseCategoryDao).isNotNull();
    }
}
