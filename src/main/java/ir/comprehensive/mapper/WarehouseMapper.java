package ir.comprehensive.mapper;

import ir.comprehensive.entity.WarehouseEntity;
import ir.comprehensive.fxmodel.WarehouseModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface WarehouseMapper extends BaseMapper<WarehouseEntity, WarehouseModel> {

    @Override
    WarehouseModel entityToModel(WarehouseEntity entity);

    @Override
    WarehouseEntity modelToEntity(WarehouseModel dto);
}
