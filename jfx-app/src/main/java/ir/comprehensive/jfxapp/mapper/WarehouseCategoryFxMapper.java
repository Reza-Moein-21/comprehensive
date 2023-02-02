package ir.comprehensive.jfxapp.mapper;

import ir.comprehensive.domain.model.WarehouseCategoryModel;
import ir.comprehensive.jfxapp.model.WarehouseCategoryFxModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface WarehouseCategoryFxMapper extends BaseFxMapper<WarehouseCategoryModel, WarehouseCategoryFxModel, Long> {

    @Override
    WarehouseCategoryFxModel modelToFxModel(WarehouseCategoryModel model);

    @Override
    WarehouseCategoryModel fxModelToModel(WarehouseCategoryFxModel dto);
}
