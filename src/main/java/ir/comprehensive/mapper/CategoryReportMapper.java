package ir.comprehensive.mapper;

import ir.comprehensive.domain.Category;
import ir.comprehensive.model.CategoryReportBean;
import ir.comprehensive.model.PersonReportBean;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CategoryReportMapper extends BaseMapper<Category, CategoryReportBean> {

    @Override
    @InheritInverseConfiguration
    CategoryReportBean entityToModel(Category entity);

    @Override
    @Mapping(target = "id", ignore = true)
    Category modelToEntity(CategoryReportBean dto);
}
