package ir.comprehensive.database.mapper;

import ir.comprehensive.database.entity.PersonEntity;
import ir.comprehensive.database.mapper.base.BaseMapper;
import ir.comprehensive.domain.model.PersonModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper extends BaseMapper<PersonEntity, PersonModel, Long> {
}
