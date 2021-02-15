package ir.comprehensive.mapper;

import ir.comprehensive.entity.WarehouseTagEntity;
import ir.comprehensive.fxmodel.WarehouseTagModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface WarehouseTagMapper extends BaseMapper<WarehouseTagEntity, WarehouseTagModel> {

    @Override
    WarehouseTagModel entityToModel(WarehouseTagEntity entity);

    @Override
    WarehouseTagEntity modelToEntity(WarehouseTagModel dto);
}
