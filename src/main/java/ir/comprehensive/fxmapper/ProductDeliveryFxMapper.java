package ir.comprehensive.fxmapper;

import ir.comprehensive.database.ProductDeliveryEntity;
import ir.comprehensive.fxmodel.ProductDeliveryFxModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProductDeliveryFxMapper extends BaseFxMapper<ProductDeliveryEntity, ProductDeliveryFxModel> {

    @Mapping(target = "title", ignore = true)
    @Mapping(target = "person.title", ignore = true)
//    @Mapping(target = "product.title", ignore = true)
    @Mapping(target = "deliveryDateFrom", ignore = true)
    @Mapping(target = "deliveryDateTo", ignore = true)
    @Mapping(target = "receivedDateFrom", ignore = true)
    @Mapping(target = "receivedDateTo", ignore = true)
    @Override
    ProductDeliveryFxModel entityToModel(ProductDeliveryEntity entity);

    @Override
    @InheritInverseConfiguration
    ProductDeliveryEntity modelToEntity(ProductDeliveryFxModel dto);
}
