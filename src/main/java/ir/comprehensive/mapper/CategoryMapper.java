package ir.comprehensive.mapper;

import ir.comprehensive.entity.CategoryEntity;
import ir.comprehensive.fxmodel.CategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<CategoryEntity, CategoryModel> {

    @Override
    CategoryModel entityToModel(CategoryEntity entity);

    @Override
    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "fax", source = "fax")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "description", source = "description")
    CategoryEntity modelToEntity(CategoryModel dto);
}
