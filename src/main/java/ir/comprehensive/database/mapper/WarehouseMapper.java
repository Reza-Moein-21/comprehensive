package ir.comprehensive.database.mapper;

import ir.comprehensive.database.WarehouseEntity;
import ir.comprehensive.database.base.BaseMapper;
import ir.comprehensive.database.model.WarehouseModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseMapper extends BaseMapper<WarehouseEntity, WarehouseModel, Long> {
}
