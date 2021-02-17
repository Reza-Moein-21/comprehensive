package ir.comprehensive.entity.mapper;

import ir.comprehensive.entity.WarehouseTagEntity;
import ir.comprehensive.entity.base.BaseMapper;
import ir.comprehensive.entity.model.WarehouseTagModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseTagMapper extends BaseMapper<WarehouseTagEntity, WarehouseTagModel, Long> {
}
