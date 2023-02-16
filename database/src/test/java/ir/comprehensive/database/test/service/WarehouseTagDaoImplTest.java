package ir.comprehensive.database.test.service;

import ir.comprehensive.database.provider.config.JooqConfig;
import ir.comprehensive.database.provider.mapper.WarehouseTagMapperImpl;
import ir.comprehensive.database.provider.service.WarehouseTagDaoImpl;
import ir.comprehensive.database.service.WarehouseTagDao;
import ir.comprehensive.domain.model.base.DomainModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@Sql(scripts = {"classpath:schema-drop.sql", "classpath:schema-create.sql", "classpath:warehouse-init.sql"})
@SpringJUnitConfig({WarehouseTagDaoImpl.class, WarehouseTagMapperImpl.class, JooqConfig.class})
public class WarehouseTagDaoImplTest {
    @Autowired
    WarehouseTagDao warehouseTagDao;

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
                .map(DomainModel::getId)
                .contains(1L);
    }

    @Test
    void givingBADAsTitleIgnoreCase_findByTitleExact_shouldReturnEmpty() {
        var result = warehouseTagDao.findByTitleExact("bAd");
        assertThat(result).isEmpty();
    }
}
