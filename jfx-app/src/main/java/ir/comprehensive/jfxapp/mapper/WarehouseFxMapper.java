package ir.comprehensive.jfxapp.mapper;

import ir.comprehensive.domain.model.WarehouseModel;
import ir.comprehensive.jfxapp.model.WarehouseFxModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface WarehouseFxMapper extends BaseFxMapper<WarehouseModel, WarehouseFxModel, Long> {

    @Override
    WarehouseFxModel modelToFxModel(WarehouseModel entity);

    @Override
    WarehouseModel fxModelToModel(WarehouseFxModel dto);
}
