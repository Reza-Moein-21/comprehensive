package ir.comprehensive.jfxapp.mapper;

import ir.comprehensive.domain.model.WarehouseTagModel;
import ir.comprehensive.jfxapp.model.WarehouseTagFxModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface WarehouseTagFxMapper extends BaseFxMapper<WarehouseTagModel, WarehouseTagFxModel, Long> {

    @Override
    WarehouseTagFxModel modelToFxModel(WarehouseTagModel model);

    @Override
    WarehouseTagModel fxModelToModel(WarehouseTagFxModel dto);
}
