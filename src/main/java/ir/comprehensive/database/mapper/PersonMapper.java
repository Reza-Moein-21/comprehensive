package ir.comprehensive.database.mapper;

import ir.comprehensive.database.PersonEntity;
import ir.comprehensive.database.base.BaseMapper;
import ir.comprehensive.database.model.PersonModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper extends BaseMapper<PersonEntity, PersonModel, Long> {
}
