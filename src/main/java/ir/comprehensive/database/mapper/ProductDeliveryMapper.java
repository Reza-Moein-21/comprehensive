package ir.comprehensive.database.mapper;

import ir.comprehensive.database.ProductDeliveryEntity;
import ir.comprehensive.database.base.BaseMapper;
import ir.comprehensive.database.model.ProductDeliveryModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDeliveryMapper extends BaseMapper<ProductDeliveryEntity, ProductDeliveryModel, Long> {
}
