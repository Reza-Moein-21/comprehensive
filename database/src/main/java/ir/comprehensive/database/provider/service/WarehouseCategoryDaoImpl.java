package ir.comprehensive.database.provider.service;

import ir.comprehensive.database.provider.mapper.WarehouseCategoryMapper;
import ir.comprehensive.database.service.WarehouseCategoryDao;
import ir.comprehensive.domain.model.WarehouseCategoryModel;
import org.jooq.DSLContext;
import org.jooq.generated.tables.records.WarehouseCategoryRecord;

import static org.jooq.generated.tables.WarehouseCategory.WAREHOUSE_CATEGORY;

public class WarehouseCategoryDaoImpl extends AbstractDescribableDomainDao<WarehouseCategoryModel, WarehouseCategoryRecord, Long> implements WarehouseCategoryDao {

    public WarehouseCategoryDaoImpl(DSLContext context, WarehouseCategoryMapper mapper) {
        super(context, mapper, WAREHOUSE_CATEGORY, WAREHOUSE_CATEGORY.ID);
    }

}
