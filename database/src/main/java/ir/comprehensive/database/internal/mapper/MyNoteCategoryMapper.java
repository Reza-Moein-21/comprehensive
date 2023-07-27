package ir.comprehensive.database.internal.mapper;

import ir.comprehensive.domain.model.MyNoteCategoryModel;
import org.jooq.generated.tables.records.MyNoteCategoryRecord;
import org.mapstruct.Mapper;

@Mapper
public interface MyNoteCategoryMapper extends BaseMapper<MyNoteCategoryModel, MyNoteCategoryRecord> {
}