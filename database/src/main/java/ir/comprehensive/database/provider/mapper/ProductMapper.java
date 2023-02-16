package ir.comprehensive.database.provider.mapper;

import ir.comprehensive.domain.model.ProductModel;
import org.jooq.generated.tables.records.ProductRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<ProductModel, ProductRecord> {
}
