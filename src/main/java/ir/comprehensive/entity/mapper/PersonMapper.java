package ir.comprehensive.entity.mapper;

import ir.comprehensive.entity.PersonEntity;
import ir.comprehensive.entity.base.BaseMapper;
import ir.comprehensive.entity.model.PersonModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PersonMapper extends BaseMapper<PersonEntity, PersonModel, Long> {
}
