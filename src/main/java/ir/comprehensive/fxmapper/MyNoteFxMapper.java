package ir.comprehensive.fxmapper;

import ir.comprehensive.entity.MyNoteEntity;
import ir.comprehensive.fxmodel.MyNoteFxModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MyNoteFxMapper extends BaseFxMapper<MyNoteEntity, MyNoteFxModel> {

    @Mapping(target = "creationDateFrom", ignore = true)
    @Mapping(target = "creationDateTo", ignore = true)
    @Mapping(target = "person.title", ignore = true)
    @Mapping(target = "myNoteCategory", ignore = true)
    @Mapping(target = "priority", source = "priority")
    @Mapping(target = "isActive", source = "isActive")
    @Mapping(target = "inActivationDate", source = "inActivationDate")
    @Mapping(target = "person", source = "person")
    @Override
    MyNoteFxModel entityToModel(MyNoteEntity entity);

    @Override
    @InheritInverseConfiguration
    MyNoteEntity modelToEntity(MyNoteFxModel dto);


}
