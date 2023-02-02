package ir.comprehensive.database.mapper;

import ir.comprehensive.database.entity.MyNoteTempEntity;
import ir.comprehensive.database.mapper.base.BaseMapper;
import ir.comprehensive.domain.model.MyNoteTempModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MyNoteTempMapper extends BaseMapper<MyNoteTempEntity, MyNoteTempModel, Long> {
}
