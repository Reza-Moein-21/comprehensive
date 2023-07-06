package ir.comprehensive.database.provider.mapper;

import ir.comprehensive.domain.model.MyNoteTempModel;
import org.jooq.generated.tables.records.MyNoteTempRecord;
import org.mapstruct.Mapper;

@Mapper
public interface MyNoteTempMapper extends BaseMapper<MyNoteTempModel, MyNoteTempRecord> {
}
