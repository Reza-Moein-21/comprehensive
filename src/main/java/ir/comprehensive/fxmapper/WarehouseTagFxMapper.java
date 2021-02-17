package ir.comprehensive.fxmapper;

import ir.comprehensive.database.WarehouseTagEntity;
import ir.comprehensive.fxmodel.WarehouseTagFxModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface WarehouseTagFxMapper extends BaseFxMapper<WarehouseTagEntity, WarehouseTagFxModel> {

    @Override
    WarehouseTagFxModel entityToModel(WarehouseTagEntity entity);

    @Override
    WarehouseTagEntity modelToEntity(WarehouseTagFxModel dto);
}
