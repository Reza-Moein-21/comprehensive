package ir.comprehensive.database.mapper;

import ir.comprehensive.database.entity.WarehouseCategoryEntity;
import ir.comprehensive.database.mapper.base.BaseMapper;
import ir.comprehensive.domain.model.WarehouseCategoryModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseCategoryMapper extends BaseMapper<WarehouseCategoryEntity, WarehouseCategoryModel, Long> {
}
