package ir.comprehensive.database.provider.mapper;

import ir.comprehensive.domain.model.CategoryModel;
import org.jooq.generated.tables.records.CategoryRecord;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<CategoryModel, CategoryRecord> {
}
