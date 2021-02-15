package ir.comprehensive.mapper;

import ir.comprehensive.entity.Warehouse;
import ir.comprehensive.fxmodel.WarehouseReportBean;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface WarehouseReportMapper extends BaseMapper<Warehouse, WarehouseReportBean> {

    @Override
    @Mapping(target = "tagList", ignore = true)
    @InheritInverseConfiguration
    WarehouseReportBean entityToModel(Warehouse entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tagList", ignore = true)
    @Mapping(target = "category.title", source = "category")
    Warehouse modelToEntity(WarehouseReportBean dto);
}
