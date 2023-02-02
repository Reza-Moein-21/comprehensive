package ir.comprehensive.database.mapper;

import ir.comprehensive.database.entity.ProductDeliveryEntity;
import ir.comprehensive.database.mapper.base.BaseMapper;
import ir.comprehensive.domain.model.ProductDeliveryModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDeliveryMapper extends BaseMapper<ProductDeliveryEntity, ProductDeliveryModel, Long> {
}
