package ir.comprehensive.fxmapper;

import ir.comprehensive.entity.PersonEntity;
import ir.comprehensive.fxmodel.PersonFxModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PersonFxMapper extends BaseFxMapper<PersonEntity, PersonFxModel> {

    @Mapping(target = "title", ignore = true)
    @Override
    PersonFxModel entityToModel(PersonEntity entity);

    @Override
    @InheritInverseConfiguration
    PersonEntity modelToEntity(PersonFxModel dto);
}
