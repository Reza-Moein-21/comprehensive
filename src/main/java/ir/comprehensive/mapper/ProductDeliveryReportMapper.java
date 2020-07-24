package ir.comprehensive.mapper;

import ir.comprehensive.domain.ProductDelivery;
import ir.comprehensive.model.ProductDeliveryReportBean;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProductDeliveryReportMapper extends BaseMapper<ProductDelivery, ProductDeliveryReportBean> {

    @Override
    @InheritInverseConfiguration
    ProductDeliveryReportBean entityToModel(ProductDelivery entity);

    @Override
    @Mapping(target = "id", ignore = true)
    ProductDelivery modelToEntity(ProductDeliveryReportBean dto);
}
