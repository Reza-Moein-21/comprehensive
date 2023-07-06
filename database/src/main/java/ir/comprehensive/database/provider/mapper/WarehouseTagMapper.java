package ir.comprehensive.database.provider.mapper;

import ir.comprehensive.domain.model.WarehouseTagModel;
import org.jooq.generated.tables.records.WarehouseTagRecord;
import org.mapstruct.Mapper;

@Mapper
public interface WarehouseTagMapper extends BaseMapper<WarehouseTagModel, WarehouseTagRecord> {
}
