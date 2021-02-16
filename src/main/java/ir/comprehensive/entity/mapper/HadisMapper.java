package ir.comprehensive.entity.mapper;

import ir.comprehensive.entity.HadisEntity;
import ir.comprehensive.entity.base.BaseMapper;
import ir.comprehensive.entity.model.HadisModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface HadisMapper extends BaseMapper<HadisEntity, HadisModel, Long> {

}
