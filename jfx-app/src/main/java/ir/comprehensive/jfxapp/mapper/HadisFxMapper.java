package ir.comprehensive.jfxapp.mapper;

import ir.comprehensive.domain.model.HadisModel;
import ir.comprehensive.jfxapp.model.HadisFxModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface HadisFxMapper extends BaseFxMapper<HadisModel, HadisFxModel, Long> {
    @Override
    HadisFxModel modelToFxModel(HadisModel model);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Override
    HadisModel fxModelToModel(HadisFxModel dto);
}
