package ir.comprehensive.mapper;

import ir.comprehensive.entity.HadisEntity;
import ir.comprehensive.fxmodel.HadisModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface HadisMapper extends BaseMapper<HadisEntity, HadisModel> {
    @Override
    HadisModel entityToModel(HadisEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Override
    HadisEntity modelToEntity(HadisModel dto);
}
