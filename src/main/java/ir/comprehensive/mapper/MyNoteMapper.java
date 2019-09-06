package ir.comprehensive.mapper;

import ir.comprehensive.domain.MyNote;
import ir.comprehensive.model.MyNoteModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MyNoteMapper extends BaseMapper<MyNote, MyNoteModel> {

    @Mapping(target = "creationDateFrom", ignore = true)
    @Mapping(target = "creationDateTo", ignore = true)
    @Mapping(target = "priority", source = "priority")
    @Mapping(target = "isActive", source = "isActive")
    @Mapping(target = "inActivationDate", source = "inActivationDate")
    @Mapping(target = "myNoteCategory", source = "myNoteCategory")
    @Override
    MyNoteModel entityToModel(MyNote entity);

    @Override
    @InheritInverseConfiguration
    MyNote modelToEntity(MyNoteModel dto);


}
