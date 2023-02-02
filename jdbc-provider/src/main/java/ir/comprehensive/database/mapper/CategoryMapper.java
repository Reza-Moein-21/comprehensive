package ir.comprehensive.database.mapper;

import ir.comprehensive.database.entity.CategoryEntity;
import ir.comprehensive.database.mapper.base.BaseMapper;
import ir.comprehensive.domain.model.CategoryModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<CategoryEntity, CategoryModel, Long> {
}
