package ir.comprehensive.database.mapper;

import ir.comprehensive.database.entity.CategoryEntity;
import ir.comprehensive.database.base.BaseMapper;
import ir.comprehensive.database.model.CategoryModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<CategoryEntity, CategoryModel, Long> {
}
