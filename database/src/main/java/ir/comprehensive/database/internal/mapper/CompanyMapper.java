package ir.comprehensive.database.internal.mapper;

import ir.comprehensive.domain.model.CompanyModel;
import org.jooq.generated.tables.records.CategoryRecord;
import org.mapstruct.Mapper;

@Mapper
public interface CompanyMapper extends BaseMapper<CompanyModel, CategoryRecord> {
}
