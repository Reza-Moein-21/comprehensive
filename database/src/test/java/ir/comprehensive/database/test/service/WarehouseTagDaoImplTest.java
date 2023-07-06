package ir.comprehensive.database.test.service;

import ir.comprehensive.database.provider.mapper.WarehouseTagMapper;
import ir.comprehensive.database.provider.service.WarehouseTagDaoImpl;
import ir.comprehensive.database.service.WarehouseTagDao;
import ir.comprehensive.database.test.utils.DBExtension;
import ir.comprehensive.database.test.utils.Sql;
import ir.comprehensive.domain.model.base.DomainModel;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("/warehouse-init.sql")
@ExtendWith(DBExtension.class)
public class WarehouseTagDaoImplTest {

    private final WarehouseTagDao warehouseTagDao;

    public WarehouseTagDaoImplTest(DSLContext context, WarehouseTagMapper mapper) {
        this.warehouseTagDao = new WarehouseTagDaoImpl(context, mapper);
    }

    @Test
    void givingNull_findByTitleExact_shouldReturnEmptyList() {
        var result = warehouseTagDao.findByTitleExact(null);
        assertThat(result).isEmpty();
    }

    @Test
    void givingNoneExistTitle_findByTitleExact_shouldReturnEmptyList() {
        var result = warehouseTagDao.findByTitleExact("<>");
        assertThat(result).isEmpty();
    }

    @Test
    void givingGOODAsTitle_findByTitleExact_shouldReturnOneItem() {
        var result = warehouseTagDao.findByTitleExact("GOOD");
        assertThat(result)
                .hasSize(1)
                .map(DomainModel::id)
                .contains(1L);
    }

    @Test
    void givingBADAsTitleIgnoreCase_findByTitleExact_shouldReturnEmpty() {
        var result = warehouseTagDao.findByTitleExact("bAd");
        assertThat(result).isEmpty();
    }
}
