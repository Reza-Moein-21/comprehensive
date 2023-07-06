package ir.comprehensive.database.provider.mapper;

import ir.comprehensive.domain.model.HadisModel;
import org.jooq.generated.tables.records.HadisRecord;
import org.mapstruct.Mapper;

@Mapper
public interface HadisMapper extends BaseMapper<HadisModel, HadisRecord> {
}
