package ir.comprehensive.mapper;

import ir.comprehensive.entity.MyNoteCategoryEntity;
import ir.comprehensive.fxmodel.MyNoteCategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MyNoteCategoryMapper extends BaseMapper<MyNoteCategoryEntity, MyNoteCategoryModel> {

    @Mapping(target = "myNotes", ignore = true)
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "countOfActive", source = "countOfActive")
    @Mapping(target = "countOfInActive", source = "countOfInActive")
    @Mapping(target = "id", source = "id")
    @Override
    MyNoteCategoryModel entityToModel(MyNoteCategoryEntity entity);

    @Override
    MyNoteCategoryEntity modelToEntity(MyNoteCategoryModel dto);
}
