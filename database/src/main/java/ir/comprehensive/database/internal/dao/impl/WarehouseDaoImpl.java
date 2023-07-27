package ir.comprehensive.database.internal.dao.impl;

import ir.comprehensive.database.exception.SearchingException;
import ir.comprehensive.database.model.PageModel;
import ir.comprehensive.database.model.PageRequestModel;
import ir.comprehensive.database.internal.mapper.WarehouseMapper;
import ir.comprehensive.database.dao.WarehouseDao;
import ir.comprehensive.domain.model.WarehouseModel;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.generated.tables.records.WarehouseRecord;

import java.util.List;

import static org.jooq.generated.tables.Warehouse.WAREHOUSE;
import static org.jooq.generated.tables.WarehouseWarehouseTag.WAREHOUSE_WAREHOUSE_TAG;
import static org.jooq.impl.DSL.lower;

public class WarehouseDaoImpl extends AbstractDescribableDomainDao<WarehouseModel, WarehouseRecord, Long> implements WarehouseDao {

    private final DSLContext context;

    public WarehouseDaoImpl(DSLContext context, WarehouseMapper mapper) {
        super(context, mapper, WAREHOUSE, WAREHOUSE.ID);
        this.context = context;
    }

    @Override
    public PageModel<WarehouseModel> findAllByCodeOrTitle(String codeTitle, PageRequestModel pageRequest) throws SearchingException {
        final var select = context.selectFrom(WAREHOUSE)
                .where(lower(WAREHOUSE.TITLE)
                        .concat(" ")
                        .concat(lower(WAREHOUSE.CODE))
                        .likeIgnoreCase("%" + codeTitle + "%"))
                .orderBy(WAREHOUSE.TITLE);
        return toPageModel(pageRequest, select);
    }

    @Override
    public List<Long> findAllIdByTagId(Long tagId) {
        return context.select(WAREHOUSE_WAREHOUSE_TAG.WAREHOUSE_ID)
                .from(WAREHOUSE_WAREHOUSE_TAG)
                .where(WAREHOUSE_WAREHOUSE_TAG.TAG_ID.equal(tagId))
                .fetch()
                .map(Record1::component1);
    }
}
