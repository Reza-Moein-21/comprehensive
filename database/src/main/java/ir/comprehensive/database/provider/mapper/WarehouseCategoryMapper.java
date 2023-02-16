package ir.comprehensive.database.provider.mapper;

import ir.comprehensive.domain.model.WarehouseCategoryModel;
import org.jooq.generated.tables.records.WarehouseCategoryRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseCategoryMapper extends BaseMapper<WarehouseCategoryModel, WarehouseCategoryRecord> {
}
