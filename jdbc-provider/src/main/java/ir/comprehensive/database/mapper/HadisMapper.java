package ir.comprehensive.database.mapper;

import ir.comprehensive.database.entity.HadisEntity;
import ir.comprehensive.database.mapper.base.BaseMapper;
import ir.comprehensive.domain.model.HadisModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HadisMapper extends BaseMapper<HadisEntity, HadisModel, Long> {

}
