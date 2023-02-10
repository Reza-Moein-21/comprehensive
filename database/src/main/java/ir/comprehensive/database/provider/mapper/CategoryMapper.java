package ir.comprehensive.database.provider.mapper;

import ir.comprehensive.domain.model.CategoryModel;
import org.jooq.generated.tables.records.CategoryRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<CategoryModel, CategoryRecord> {
    @Override
    CategoryModel recordToModel(CategoryRecord record);
}
