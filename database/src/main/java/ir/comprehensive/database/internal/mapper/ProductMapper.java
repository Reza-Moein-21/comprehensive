package ir.comprehensive.database.internal.mapper;

import ir.comprehensive.domain.model.ProductModel;
import org.jooq.generated.tables.records.ProductRecord;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<ProductModel, ProductRecord> {
}