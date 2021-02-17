package ir.comprehensive.fxmapper;

import ir.comprehensive.database.MyNoteCategoryEntity;
import ir.comprehensive.fxmodel.MyNoteCategoryFxModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MyNoteCategoryFxMapper extends BaseFxMapper<MyNoteCategoryEntity, MyNoteCategoryFxModel> {

    @Mapping(target = "myNotes", ignore = true)
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "countOfActive", source = "countOfActive")
    @Mapping(target = "countOfInActive", source = "countOfInActive")
    @Mapping(target = "id", source = "id")
    @Override
    MyNoteCategoryFxModel entityToModel(MyNoteCategoryEntity entity);

    @Override
    MyNoteCategoryEntity modelToEntity(MyNoteCategoryFxModel dto);
}
