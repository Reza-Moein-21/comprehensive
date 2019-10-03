package ir.comprehensive.mapper;

import ir.comprehensive.domain.WarehouseTag;
import ir.comprehensive.model.WarehouseTagModel;
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
