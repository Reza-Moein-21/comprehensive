package ir.comprehensive.entity.mapper;

import ir.comprehensive.entity.WarehouseEntity;
import ir.comprehensive.entity.base.BaseMapper;
import ir.comprehensive.entity.model.WarehouseModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseMapper extends BaseMapper<WarehouseEntity, WarehouseModel, Long> {
}
