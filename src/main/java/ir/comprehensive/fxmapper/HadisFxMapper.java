package ir.comprehensive.fxmapper;

import ir.comprehensive.database.entity.HadisEntity;
import ir.comprehensive.fxmodel.HadisFxModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface HadisFxMapper extends BaseFxMapper<HadisEntity, HadisFxModel> {
    @Override
    HadisFxModel entityToModel(HadisEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Override
    HadisEntity modelToEntity(HadisFxModel dto);
}
