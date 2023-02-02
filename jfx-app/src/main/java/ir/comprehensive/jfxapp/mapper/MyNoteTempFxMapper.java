package ir.comprehensive.jfxapp.mapper;

import ir.comprehensive.domain.model.MyNoteTempModel;
import ir.comprehensive.jfxapp.model.MyNoteTempFxModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MyNoteTempFxMapper extends BaseFxMapper<MyNoteTempModel, MyNoteTempFxModel, Long> {

    @Mapping(source = "myNote.title", target = "title")
    @Mapping(source = "myNote.description", target = "description")
    @Mapping(source = "myNote.priority", target = "priority")
    @Mapping(source = "myNote.myNoteCategory.title", target = "projectName")
    @Mapping(source = "myNote.person", target = "person")
    @Override
    MyNoteTempFxModel modelToFxModel(MyNoteTempModel model);

    @Override
    MyNoteTempModel fxModelToModel(MyNoteTempFxModel dto);
}
