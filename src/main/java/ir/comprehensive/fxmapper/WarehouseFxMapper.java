package ir.comprehensive.fxmapper;

import ir.comprehensive.database.WarehouseEntity;
import ir.comprehensive.fxmodel.WarehouseFxModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface WarehouseFxMapper extends BaseFxMapper<WarehouseEntity, WarehouseFxModel> {

    @Override
    WarehouseFxModel entityToModel(WarehouseEntity entity);

    @Override
    WarehouseEntity modelToEntity(WarehouseFxModel dto);
}
