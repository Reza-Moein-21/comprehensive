package ir.comprehensive.mapper;

import ir.comprehensive.domain.Person;
import ir.comprehensive.model.PersonModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PersonMapper extends BaseMapper<Person, PersonModel> {

    @Override
    PersonModel entityToModel(Person entity);

    @Override
    @InheritInverseConfiguration
    Person modelToEntity(PersonModel dto);
}
