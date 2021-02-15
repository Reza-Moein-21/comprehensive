package ir.comprehensive.mapper;

import ir.comprehensive.entity.Warehouse;
import ir.comprehensive.model.WarehouseModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface WarehouseMapper extends BaseMapper<Warehouse, WarehouseModel> {

    @Override
    WarehouseModel entityToModel(Warehouse entity);

    @Override
    Warehouse modelToEntity(WarehouseModel dto);
}
