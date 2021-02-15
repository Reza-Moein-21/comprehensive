package ir.comprehensive.mapper;

import ir.comprehensive.entity.WarehouseEntity;
import ir.comprehensive.fxmodel.WarehouseReportBean;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface WarehouseReportMapper extends BaseMapper<WarehouseEntity, WarehouseReportBean> {

    @Override
    @Mapping(target = "tagList", ignore = true)
    @InheritInverseConfiguration
    WarehouseReportBean entityToModel(WarehouseEntity entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tagList", ignore = true)
    @Mapping(target = "category.title", source = "category")
    WarehouseEntity modelToEntity(WarehouseReportBean dto);
}
