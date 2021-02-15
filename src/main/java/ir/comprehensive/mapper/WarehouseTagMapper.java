package ir.comprehensive.mapper;

import ir.comprehensive.entity.WarehouseTag;
import ir.comprehensive.fxmodel.WarehouseTagModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface WarehouseTagMapper extends BaseMapper<WarehouseTag, WarehouseTagModel> {

    @Override
    WarehouseTagModel entityToModel(WarehouseTag entity);

    @Override
    WarehouseTag modelToEntity(WarehouseTagModel dto);
}
