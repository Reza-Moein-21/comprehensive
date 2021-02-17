package ir.comprehensive.fxmapper;

import ir.comprehensive.database.PersonEntity;
import ir.comprehensive.fxmodel.PersonReportBean;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PersonReportMapper extends BaseFxMapper<PersonEntity, PersonReportBean> {

    @Override
    @InheritInverseConfiguration
    PersonReportBean entityToModel(PersonEntity entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "categories", ignore = true)
    PersonEntity modelToEntity(PersonReportBean dto);
}
