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

    @Override
    @Mapping(target = "id", source = "id")
    @Mapping(target = "deliveryDate", source = "deliveryDate")
    @Mapping(target = "desiredDate", source = "desiredDate")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "person", source = "person")
    @Mapping(target = "productName", source = "product.name")
    ProductDeliveryModel entityToModel(ProductDelivery entity);

    @Override
    @InheritInverseConfiguration
    ProductDelivery modelToEntity(ProductDeliveryModel dto);
}