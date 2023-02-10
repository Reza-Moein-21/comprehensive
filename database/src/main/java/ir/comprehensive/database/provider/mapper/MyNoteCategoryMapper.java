package ir.comprehensive.database.provider.mapper;

import ir.comprehensive.domain.model.MyNoteCategoryModel;
import org.jooq.generated.tables.records.MyNoteCategoryRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MyNoteCategoryMapper extends BaseMapper<MyNoteCategoryModel, MyNoteCategoryRecord> {
    @Override
    MyNoteCategoryModel recordToModel(MyNoteCategoryRecord record);
}
