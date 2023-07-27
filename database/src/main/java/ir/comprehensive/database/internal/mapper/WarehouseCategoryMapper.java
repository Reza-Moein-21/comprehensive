package ir.comprehensive.database.internal.mapper;

import ir.comprehensive.domain.model.WarehouseCategoryModel;
import org.jooq.generated.tables.records.WarehouseCategoryRecord;
import org.mapstruct.Mapper;

@Mapper
public interface WarehouseCategoryMapper extends BaseMapper<WarehouseCategoryModel, WarehouseCategoryRecord> {
}
