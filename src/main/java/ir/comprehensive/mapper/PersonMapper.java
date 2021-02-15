package ir.comprehensive.mapper;

import ir.comprehensive.entity.Person;
import ir.comprehensive.fxmodel.PersonModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PersonMapper extends BaseMapper<Person, PersonModel> {

    @Mapping(target = "title", ignore = true)
    @Override
    PersonModel entityToModel(Person entity);

    @Override
    @InheritInverseConfiguration
    Person modelToEntity(PersonModel dto);
}
