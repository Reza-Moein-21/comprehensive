package ir.comprehensive.database.provider.mapper;

import ir.comprehensive.domain.model.MyNoteModel;
import org.jooq.generated.tables.records.MyNoteRecord;
import org.mapstruct.Mapper;

@Mapper
public interface MyNoteMapper extends BaseMapper<MyNoteModel, MyNoteRecord> {
}
