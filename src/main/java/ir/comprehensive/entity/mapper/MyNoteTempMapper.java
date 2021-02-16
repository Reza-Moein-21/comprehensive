package ir.comprehensive.entity.mapper;

import ir.comprehensive.entity.MyNoteTempEntity;
import ir.comprehensive.entity.base.BaseMapper;
import ir.comprehensive.entity.model.MyNoteTempModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MyNoteTempMapper extends BaseMapper<MyNoteTempEntity, MyNoteTempModel, Long> {
}
