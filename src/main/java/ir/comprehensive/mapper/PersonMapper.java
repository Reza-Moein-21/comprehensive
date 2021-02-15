package ir.comprehensive.mapper;

import ir.comprehensive.entity.PersonEntity;
import ir.comprehensive.fxmodel.PersonModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PersonMapper extends BaseMapper<PersonEntity, PersonModel> {

    @Mapping(target = "title", ignore = true)
    @Override
    PersonModel entityToModel(PersonEntity entity);

    @Override
    @InheritInverseConfiguration
    PersonEntity modelToEntity(PersonModel dto);
}
