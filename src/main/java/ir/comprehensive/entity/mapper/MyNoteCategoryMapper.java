package ir.comprehensive.entity.mapper;

import ir.comprehensive.entity.MyNoteCategoryEntity;
import ir.comprehensive.entity.base.BaseMapper;
import ir.comprehensive.entity.model.MyNoteCategoryModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MyNoteCategoryMapper extends BaseMapper<MyNoteCategoryEntity, MyNoteCategoryModel, Long> {
}
