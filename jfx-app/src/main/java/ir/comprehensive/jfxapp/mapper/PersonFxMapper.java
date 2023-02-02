package ir.comprehensive.jfxapp.mapper;

import ir.comprehensive.domain.model.PersonModel;
import ir.comprehensive.jfxapp.model.PersonFxModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PersonFxMapper extends BaseFxMapper<PersonModel, PersonFxModel, Long> {

    @Mapping(target = "title", ignore = true)
    @Override
    PersonFxModel modelToFxModel(PersonModel model);

    @Override
    @InheritInverseConfiguration
    PersonModel fxModelToModel(PersonFxModel dto);
}
