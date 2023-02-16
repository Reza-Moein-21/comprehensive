package ir.comprehensive.database.provider.mapper;

import ir.comprehensive.domain.model.WarehouseModel;
import org.jooq.generated.tables.records.WarehouseRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseMapper extends BaseMapper<WarehouseModel, WarehouseRecord> {
}
