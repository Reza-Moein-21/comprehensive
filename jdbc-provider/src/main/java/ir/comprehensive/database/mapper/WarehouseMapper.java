package ir.comprehensive.database.mapper;

import ir.comprehensive.database.entity.WarehouseEntity;
import ir.comprehensive.database.mapper.base.BaseMapper;
import ir.comprehensive.domain.model.WarehouseModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseMapper extends BaseMapper<WarehouseEntity, WarehouseModel, Long> {
}
