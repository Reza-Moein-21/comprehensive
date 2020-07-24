package ir.comprehensive.mapper;

import ir.comprehensive.domain.Person;
import ir.comprehensive.model.PersonReportBean;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PersonReportMapper extends BaseMapper<Person, PersonReportBean> {

    @Override
    @InheritInverseConfiguration
    PersonReportBean entityToModel(Person entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Person modelToEntity(PersonReportBean dto);
}
