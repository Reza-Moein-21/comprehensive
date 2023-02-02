package ir.comprehensive.jfxapp.mapper;

import ir.comprehensive.domain.model.MyNoteCategoryModel;
import ir.comprehensive.jfxapp.model.MyNoteCategoryFxModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MyNoteCategoryFxMapper extends BaseFxMapper<MyNoteCategoryModel, MyNoteCategoryFxModel, Long> {

    @Mapping(target = "myNotes", ignore = true)
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "countOfActive", source = "countOfActive")
    @Mapping(target = "countOfInActive", source = "countOfInActive")
    @Mapping(target = "id", source = "id")
    @Override
    MyNoteCategoryFxModel modelToFxModel(MyNoteCategoryModel model);

    @Override
    MyNoteCategoryModel fxModelToModel(MyNoteCategoryFxModel dto);
}
