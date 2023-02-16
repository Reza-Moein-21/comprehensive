package ir.comprehensive.database.test.service;

import ir.comprehensive.database.model.PageRequestModel;
import ir.comprehensive.database.provider.config.JooqConfig;
import ir.comprehensive.database.provider.mapper.WarehouseMapperImpl;
import ir.comprehensive.database.provider.service.WarehouseDaoImpl;
import ir.comprehensive.database.service.WarehouseDao;
import ir.comprehensive.domain.model.base.DomainModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@Sql(scripts = {"classpath:schema-drop.sql", "classpath:schema-create.sql", "classpath:warehouse-init.sql"})
@SpringJUnitConfig({WarehouseDaoImpl.class, WarehouseMapperImpl.class, JooqConfig.class})
public class WarehouseDaoImplTest {
    @Autowired
    WarehouseDao warehouseDao;

    @Test
    void givingNullCodeTitle_findAllByCodeOrTitle_shouldReturnEmptyPage() {
        var result = warehouseDao.findAllByCodeOrTitle(null, PageRequestModel.ofSize(10));
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    void givingNoneExistCodeTitle_findAllByCodeOrTitle_shouldReturnEmptyPage() {
        var result = warehouseDao.findAllByCodeOrTitle("<>", PageRequestModel.ofSize(10));
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    void givingCodeAndTitle_findAllByCodeOrTitle_shouldReturnEmpty() {
        var result = warehouseDao.findAllByCodeOrTitle("R1Resistor", PageRequestModel.ofSize(10));
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    void givingOnlyTitle_findAllByCodeOrTitle_shouldReturnOneItem() {
        var result = warehouseDao.findAllByCodeOrTitle("Resistor", PageRequestModel.ofSize(10));
        assertThat(result.totalPages()).isOne();
        assertThat(result.totalItems()).isEqualTo(4);
        assertThat(result.currentPage()).isOne();
        assertThat(result.numberOfElements()).isOne();
        assertThat(result.content())
                .map(DomainModel::getId)
                .contains(115L);
    }

    @Test
    void givingOnlyCode_findAllByCodeOrTitle_shouldReturnOneItem() {
        var result = warehouseDao.findAllByCodeOrTitle("R1", PageRequestModel.ofSize(10));
        assertThat(result.totalPages()).isOne();
        assertThat(result.totalItems()).isEqualTo(4);
        assertThat(result.currentPage()).isOne();
        assertThat(result.numberOfElements()).isOne();
        assertThat(result.content())
                .map(DomainModel::getId)
                .contains(115L);
    }

    @Test
    void givingCodeLike_findAllByCodeOrTitle_shouldReturnOneItem() {
        var result = warehouseDao.findAllByCodeOrTitle("C3", PageRequestModel.ofSize(10));
        assertThat(result.totalPages()).isOne();
        assertThat(result.totalItems()).isEqualTo(4);
        assertThat(result.currentPage()).isOne();
        assertThat(result.numberOfElements()).isOne();
        assertThat(result.content())
                .map(DomainModel::getId)
                .contains(114L);
    }

    @Test
    void givingTitleLike_findAllByCodeOrTitle_shouldReturnOneItem() {
        var result = warehouseDao.findAllByCodeOrTitle("l2", PageRequestModel.ofSize(10));
        assertThat(result.totalPages()).isOne();
        assertThat(result.totalItems()).isEqualTo(4);
        assertThat(result.currentPage()).isOne();
        assertThat(result.numberOfElements()).isOne();
        assertThat(result.content())
                .map(DomainModel::getId)
                .contains(113L);
    }

    @Test
    void givingNullTag_findAllIdByTagId_shouldReturnEmpty() {
        var result = warehouseDao.findAllIdByTagId(null);
        assertThat(result).isEmpty();
    }

    @Test
    void givingNoneExistTag_findAllIdByTagId_shouldReturnEmpty() {
        var result = warehouseDao.findAllIdByTagId(-1L);
        assertThat(result).isEmpty();
    }

    @Test
    void givingValidTag_findAllIdByTagId_shouldReturnListOfThree() {
        var result = warehouseDao.findAllIdByTagId(1L);
        assertThat(result).contains(112L, 113L, 114L);
    }
}
