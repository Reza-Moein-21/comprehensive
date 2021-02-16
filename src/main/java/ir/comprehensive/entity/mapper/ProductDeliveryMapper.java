package ir.comprehensive.entity.mapper;

import ir.comprehensive.entity.ProductDeliveryEntity;
import ir.comprehensive.entity.base.BaseMapper;
import ir.comprehensive.entity.model.ProductDeliveryModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProductDeliveryMapper extends BaseMapper<ProductDeliveryEntity, ProductDeliveryModel, Long> {
}
