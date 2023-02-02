package ir.comprehensive.database.provider.mapper;

import ir.comprehensive.domain.model.PersonModel;
import org.jooq.generated.tables.records.PersonRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper extends BaseMapper<PersonModel, PersonRecord> {
    @Override
    @Mapping(target = "title", expression = "java(record.getFirstName() + \" \" + record.getLastName())")
    PersonModel recordToModel(PersonRecord record);
}
