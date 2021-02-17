package ir.comprehensive.fxmapper;

import ir.comprehensive.database.MyNoteTempEntity;
import ir.comprehensive.fxmodel.MyNoteTempFxModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MyNoteTempFxMapper extends BaseFxMapper<MyNoteTempEntity, MyNoteTempFxModel> {

    @Mapping(source = "myNote.title", target = "title")
    @Mapping(source = "myNote.description", target = "description")
    @Mapping(source = "myNote.priority", target = "priority")
    @Mapping(source = "myNote.myNoteCategory.title", target = "projectName")
    @Mapping(source = "myNote.person", target = "person")
    @Override
    MyNoteTempFxModel entityToModel(MyNoteTempEntity entity);

    @Override
    MyNoteTempEntity modelToEntity(MyNoteTempFxModel dto);
}
