package ir.comprehensive.entity.mapper;

import ir.comprehensive.entity.CategoryEntity;
import ir.comprehensive.entity.base.BaseMapper;
import ir.comprehensive.entity.model.CategoryModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<CategoryEntity, CategoryModel, Long> {
}
