package ir.comprehensive.mapper;

import ir.comprehensive.domain.ProductDelivery;
import ir.comprehensive.model.ProductDeliveryModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProductDeliveryMapper extends BaseMapper<ProductDelivery, ProductDeliveryModel> {

    @Mapping(target = "productName", source = "product.name")
    @Override
    ProductDeliveryModel entityToModel(ProductDelivery entity);

    @Override
    @Mapping(target = "receivedDate", ignore = true)
    @Mapping(target = "state", ignore = true)
    @InheritInverseConfiguration
    ProductDelivery modelToEntity(ProductDeliveryModel dto);
}
