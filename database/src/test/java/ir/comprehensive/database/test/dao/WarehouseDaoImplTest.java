package ir.comprehensive.database.test.dao;

import ir.comprehensive.database.model.PageRequestModel;
import ir.comprehensive.database.internal.mapper.WarehouseMapper;
import ir.comprehensive.database.internal.dao.impl.WarehouseDaoImpl;
import ir.comprehensive.database.dao.WarehouseDao;
import ir.comprehensive.database.test.utils.DBExtension;
import ir.comprehensive.database.test.utils.Sql;
import ir.comprehensive.domain.model.base.DomainModel;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("/warehouse-init.sql")
@ExtendWith(DBExtension.class)
public class WarehouseDaoImplTest {

    private final WarehouseDao warehouseDao;

    public WarehouseDaoImplTest(DSLContext context, WarehouseMapper mapper) {
        this.warehouseDao = new WarehouseDaoImpl(context, mapper);
    }

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
                .map(DomainModel::id)
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
                .map(DomainModel::id)
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
                .map(DomainModel::id)
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
                .map(DomainModel::id)
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
