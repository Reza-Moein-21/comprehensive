package ir.comprehensive.fxmapper;

import ir.comprehensive.entity.WarehouseCategoryEntity;
import ir.comprehensive.fxmodel.WarehouseCategoryFxModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface WarehouseCategoryFxMapper extends BaseFxMapper<WarehouseCategoryEntity, WarehouseCategoryFxModel> {

    @Override
    WarehouseCategoryFxModel entityToModel(WarehouseCategoryEntity entity);

    @Override
    WarehouseCategoryEntity modelToEntity(WarehouseCategoryFxModel dto);
}
