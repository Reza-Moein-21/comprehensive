package ir.comprehensive.database.mapper;

import ir.comprehensive.database.MyNoteTempEntity;
import ir.comprehensive.database.base.BaseMapper;
import ir.comprehensive.database.model.MyNoteTempModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MyNoteTempMapper extends BaseMapper<MyNoteTempEntity, MyNoteTempModel, Long> {
}
