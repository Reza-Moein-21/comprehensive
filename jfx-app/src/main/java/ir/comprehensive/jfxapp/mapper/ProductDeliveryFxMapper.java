package ir.comprehensive.jfxapp.mapper;

import ir.comprehensive.domain.model.ProductDeliveryModel;
import ir.comprehensive.jfxapp.model.ProductDeliveryFxModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProductDeliveryFxMapper extends BaseFxMapper<ProductDeliveryModel, ProductDeliveryFxModel, Long> {

    @Mapping(target = "title", ignore = true)
    @Mapping(target = "person.title", ignore = true)
//    @Mapping(target = "product.title", ignore = true)
    @Mapping(target = "deliveryDateFrom", ignore = true)
    @Mapping(target = "deliveryDateTo", ignore = true)
    @Mapping(target = "receivedDateFrom", ignore = true)
    @Mapping(target = "receivedDateTo", ignore = true)
    @Override
    ProductDeliveryFxModel modelToFxModel(ProductDeliveryModel model);

    @Override
    @InheritInverseConfiguration
    ProductDeliveryModel fxModelToModel(ProductDeliveryFxModel dto);
}
