package ir.comprehensive.jfxapp.mapper;

import ir.comprehensive.domain.model.MyNoteModel;
import ir.comprehensive.jfxapp.model.MyNoteFxModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MyNoteFxMapper extends BaseFxMapper<MyNoteModel, MyNoteFxModel, Long> {

    @Mapping(target = "creationDateFrom", ignore = true)
    @Mapping(target = "creationDateTo", ignore = true)
    @Mapping(target = "person.title", ignore = true)
    @Mapping(target = "myNoteCategory", ignore = true)
    @Mapping(target = "priority", source = "priority")
    @Mapping(target = "isActive", source = "isActive")
    @Mapping(target = "inActivationDate", source = "inActivationDate")
    @Mapping(target = "person", source = "person")
    @Override
    MyNoteFxModel modelToFxModel(MyNoteModel model);

    @Override
    @InheritInverseConfiguration
    MyNoteModel fxModelToModel(MyNoteFxModel dto);


}
