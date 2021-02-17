package ir.comprehensive.database.mapper;

import ir.comprehensive.database.entity.MyNoteCategoryEntity;
import ir.comprehensive.database.base.BaseMapper;
import ir.comprehensive.database.model.MyNoteCategoryModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MyNoteCategoryMapper extends BaseMapper<MyNoteCategoryEntity, MyNoteCategoryModel, Long> {
}
