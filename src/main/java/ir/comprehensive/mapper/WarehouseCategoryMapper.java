package ir.comprehensive.mapper;

import ir.comprehensive.entity.WarehouseCategory;
import ir.comprehensive.fxmodel.WarehouseCategoryModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface WarehouseCategoryMapper extends BaseMapper<WarehouseCategory, WarehouseCategoryModel> {

    @Override
    WarehouseCategoryModel entityToModel(WarehouseCategory entity);

    @Override
    WarehouseCategory modelToEntity(WarehouseCategoryModel dto);
}
