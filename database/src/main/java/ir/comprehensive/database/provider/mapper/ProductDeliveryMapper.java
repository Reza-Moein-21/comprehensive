package ir.comprehensive.database.provider.mapper;

import ir.comprehensive.domain.model.ProductDeliveryModel;
import org.jooq.generated.tables.records.ProductDeliveryRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDeliveryMapper extends BaseMapper<ProductDeliveryModel, ProductDeliveryRecord> {
}
