package ir.comprehensive.database.internal.dao.impl;

import ir.comprehensive.database.internal.mapper.WarehouseTagMapper;
import ir.comprehensive.database.dao.WarehouseTagDao;
import ir.comprehensive.domain.model.WarehouseTagModel;
import org.jooq.DSLContext;
import org.jooq.generated.tables.records.WarehouseTagRecord;

import static org.jooq.generated.tables.WarehouseTag.WAREHOUSE_TAG;

public class WarehouseTagDaoImpl extends AbstractDescribableDomainDao<WarehouseTagModel, WarehouseTagRecord, Long> implements WarehouseTagDao {

    public WarehouseTagDaoImpl(DSLContext context, WarehouseTagMapper mapper) {
        super(context, mapper, WAREHOUSE_TAG, WAREHOUSE_TAG.ID);
    }

}