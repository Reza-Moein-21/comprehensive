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

    @Mapping(target = "onEdit", ignore = true)
    @Mapping(target = "btnEdit", ignore = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "categories", source = "categories", ignore = true)
    @Override
    PersonModel entityToModel(Person entity);

    @Override
    @InheritInverseConfiguration
    Person modelToEntity(PersonModel dto);
}
