package ir.comprehensive.mapper;

import ir.comprehensive.entity.MyNoteTempEntity;
import ir.comprehensive.fxmodel.MyNoteTempModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MyNoteTempMapper extends BaseMapper<MyNoteTempEntity, MyNoteTempModel> {

    @Mapping(source = "myNote.title", target = "title")
    @Mapping(source = "myNote.description", target = "description")
    @Mapping(source = "myNote.priority", target = "priority")
    @Mapping(source = "myNote.myNoteCategory.title", target = "projectName")
    @Mapping(source = "myNote.person", target = "person")
    @Override
    MyNoteTempModel entityToModel(MyNoteTempEntity entity);

    @Override
    MyNoteTempEntity modelToEntity(MyNoteTempModel dto);
}
