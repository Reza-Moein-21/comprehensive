package ir.comprehensive.database.mapper;

import ir.comprehensive.database.entity.MyNoteCategoryEntity;
import ir.comprehensive.database.mapper.base.BaseMapper;
import ir.comprehensive.domain.model.MyNoteCategoryModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MyNoteCategoryMapper extends BaseMapper<MyNoteCategoryEntity, MyNoteCategoryModel, Long> {
}
