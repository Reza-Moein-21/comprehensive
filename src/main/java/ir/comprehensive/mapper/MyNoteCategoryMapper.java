package ir.comprehensive.mapper;

import ir.comprehensive.entity.MyNoteCategory;
import ir.comprehensive.model.MyNoteCategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MyNoteCategoryMapper extends BaseMapper<MyNoteCategory, MyNoteCategoryModel> {

    @Mapping(target = "myNotes", ignore = true)
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "countOfActive", source = "countOfActive")
    @Mapping(target = "countOfInActive", source = "countOfInActive")
    @Mapping(target = "id", source = "id")
    @Override
    MyNoteCategoryModel entityToModel(MyNoteCategory entity);

    @Override
    MyNoteCategory modelToEntity(MyNoteCategoryModel dto);
}
