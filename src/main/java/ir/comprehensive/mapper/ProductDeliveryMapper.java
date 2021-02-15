package ir.comprehensive.mapper;

import ir.comprehensive.entity.ProductDeliveryEntity;
import ir.comprehensive.fxmodel.ProductDeliveryModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProductDeliveryMapper extends BaseMapper<ProductDeliveryEntity, ProductDeliveryModel> {

    @Mapping(target = "title", ignore = true)
    @Mapping(target = "person.title", ignore = true)
//    @Mapping(target = "product.title", ignore = true)
    @Mapping(target = "deliveryDateFrom", ignore = true)
    @Mapping(target = "deliveryDateTo", ignore = true)
    @Mapping(target = "receivedDateFrom", ignore = true)
    @Mapping(target = "receivedDateTo", ignore = true)
    @Override
    ProductDeliveryModel entityToModel(ProductDeliveryEntity entity);

    @Override
    @InheritInverseConfiguration
    ProductDeliveryEntity modelToEntity(ProductDeliveryModel dto);
}
