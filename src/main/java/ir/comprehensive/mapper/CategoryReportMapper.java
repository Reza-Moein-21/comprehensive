package ir.comprehensive.mapper;

import ir.comprehensive.entity.CategoryEntity;
import ir.comprehensive.fxmodel.CategoryReportBean;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CategoryReportMapper extends BaseMapper<CategoryEntity, CategoryReportBean> {

    @Override
    @InheritInverseConfiguration
    CategoryReportBean entityToModel(CategoryEntity entity);

    @Override
    @Mapping(target = "id", ignore = true)
    CategoryEntity modelToEntity(CategoryReportBean dto);
}