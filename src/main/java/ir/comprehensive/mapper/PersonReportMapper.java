package ir.comprehensive.mapper;

import ir.comprehensive.entity.PersonEntity;
import ir.comprehensive.fxmodel.PersonReportBean;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PersonReportMapper extends BaseMapper<PersonEntity, PersonReportBean> {

    @Override
    @InheritInverseConfiguration
    PersonReportBean entityToModel(PersonEntity entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "categories", ignore = true)
    PersonEntity modelToEntity(PersonReportBean dto);
}
