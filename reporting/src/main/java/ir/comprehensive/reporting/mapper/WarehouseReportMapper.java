package ir.comprehensive.reporting.mapper;

import ir.comprehensive.domain.model.WarehouseModel;
import ir.comprehensive.reporting.model.WarehouseReportBean;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface WarehouseReportMapper extends BaseReportMapper<WarehouseModel, WarehouseReportBean, Long> {

    @Override
    @Mapping(target = "tagList", ignore = true)
    @InheritInverseConfiguration
    WarehouseReportBean modelToReportBean(WarehouseModel entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tagList", ignore = true)
    @Mapping(target = "category.title", source = "category")
    WarehouseModel reportBeanToModel(WarehouseReportBean dto);
}
