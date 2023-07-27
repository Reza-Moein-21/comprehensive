package ir.comprehensive.database.internal.mapper;

import ir.comprehensive.domain.model.ProductDeliveryModel;
import org.jooq.generated.tables.records.ProductDeliveryRecord;
import org.mapstruct.Mapper;

@Mapper
public interface ProductDeliveryMapper extends BaseMapper<ProductDeliveryModel, ProductDeliveryRecord> {
}