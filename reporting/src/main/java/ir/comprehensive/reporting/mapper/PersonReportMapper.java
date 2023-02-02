package ir.comprehensive.reporting.mapper;

import ir.comprehensive.domain.model.PersonModel;
import ir.comprehensive.reporting.model.PersonReportBean;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PersonReportMapper extends BaseReportMapper<PersonModel, PersonReportBean, Long> {

    @Override
    @InheritInverseConfiguration
    PersonReportBean modelToReportBean(PersonModel model);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "categories", ignore = true)
    PersonModel reportBeanToModel(PersonReportBean dto);
}
