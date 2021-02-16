package ir.comprehensive.entity.mapper;

import ir.comprehensive.entity.WarehouseCategoryEntity;
import ir.comprehensive.entity.base.BaseMapper;
import ir.comprehensive.entity.model.WarehouseCategoryModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface WarehouseCategoryMapper extends BaseMapper<WarehouseCategoryEntity, WarehouseCategoryModel, Long> {
}
