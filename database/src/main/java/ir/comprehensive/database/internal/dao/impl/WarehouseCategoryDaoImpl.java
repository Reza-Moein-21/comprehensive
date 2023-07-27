package ir.comprehensive.database.internal.dao.impl;

import ir.comprehensive.database.internal.mapper.WarehouseCategoryMapper;
import ir.comprehensive.database.dao.WarehouseCategoryDao;
import ir.comprehensive.domain.model.WarehouseCategoryModel;
import org.jooq.DSLContext;
import org.jooq.generated.tables.records.WarehouseCategoryRecord;

import static org.jooq.generated.tables.WarehouseCategory.WAREHOUSE_CATEGORY;

public class WarehouseCategoryDaoImpl extends AbstractDescribableDomainDao<WarehouseCategoryModel, WarehouseCategoryRecord, Long> implements WarehouseCategoryDao {

    public WarehouseCategoryDaoImpl(DSLContext context, WarehouseCategoryMapper mapper) {
        super(context, mapper, WAREHOUSE_CATEGORY, WAREHOUSE_CATEGORY.ID);
    }

}
