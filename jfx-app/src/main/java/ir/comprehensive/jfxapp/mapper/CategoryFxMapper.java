package ir.comprehensive.jfxapp.mapper;

import ir.comprehensive.domain.model.CategoryModel;
import ir.comprehensive.jfxapp.model.CategoryFxModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CategoryFxMapper extends BaseFxMapper<CategoryModel, CategoryFxModel, Long> {

    @Override
    CategoryFxModel modelToFxModel(CategoryModel model);

    @Override
    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "fax", source = "fax")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "description", source = "description")
    CategoryModel fxModelToModel(CategoryFxModel fxModel);
}
