package ir.comprehensive.mapper;

import ir.comprehensive.entity.WarehouseCategoryEntity;
import ir.comprehensive.fxmodel.WarehouseCategoryModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface WarehouseCategoryMapper extends BaseMapper<WarehouseCategoryEntity, WarehouseCategoryModel> {

    @Override
    WarehouseCategoryModel entityToModel(WarehouseCategoryEntity entity);

    @Override
    WarehouseCategoryEntity modelToEntity(WarehouseCategoryModel dto);
}
