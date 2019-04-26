package ir.comprehensive.mapper;

import ir.comprehensive.domain.Person;
import ir.comprehensive.model.PersonModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PersonMapper extends BaseMapper<Person, PersonModel> {

    @Mapping(target = "groupedValue", ignore = true)
    @Mapping(target = "groupedColumn", ignore = true)
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "fullName", source = "fullName")
    @Override
    PersonModel entityToDto(Person entity);

    @Override
    @InheritInverseConfiguration
    Person dtoToEntity(PersonModel dto);
}
