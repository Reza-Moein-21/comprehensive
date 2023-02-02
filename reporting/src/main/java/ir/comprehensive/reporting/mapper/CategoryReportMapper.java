package ir.comprehensive.reporting.mapper;

import ir.comprehensive.domain.model.CategoryModel;
import ir.comprehensive.reporting.model.CategoryReportBean;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CategoryReportMapper extends BaseReportMapper<CategoryModel, CategoryReportBean, Long> {

    @Override
    @InheritInverseConfiguration
    CategoryReportBean modelToReportBean(CategoryModel model);

    @Override
    @Mapping(target = "id", ignore = true)
    CategoryModel reportBeanToModel(CategoryReportBean reportBean);
}
