package ir.comprehensive.database.mapper;

import ir.comprehensive.database.entity.WarehouseTagEntity;
import ir.comprehensive.database.base.BaseMapper;
import ir.comprehensive.database.model.WarehouseTagModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseTagMapper extends BaseMapper<WarehouseTagEntity, WarehouseTagModel, Long> {
}
