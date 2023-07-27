package ir.comprehensive.database.internal.mapper;

import ir.comprehensive.domain.model.WarehouseModel;
import org.jooq.generated.tables.records.WarehouseRecord;
import org.mapstruct.Mapper;

@Mapper
public interface WarehouseMapper extends BaseMapper<WarehouseModel, WarehouseRecord> {
}
