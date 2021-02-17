package ir.comprehensive.database.mapper;

import ir.comprehensive.database.HadisEntity;
import ir.comprehensive.database.base.BaseMapper;
import ir.comprehensive.database.model.HadisModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HadisMapper extends BaseMapper<HadisEntity, HadisModel, Long> {

}
