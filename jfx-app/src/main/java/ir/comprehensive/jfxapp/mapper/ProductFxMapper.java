package ir.comprehensive.jfxapp.mapper;

import ir.comprehensive.domain.model.ProductModel;
import ir.comprehensive.jfxapp.model.ProductFxModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProductFxMapper extends BaseFxMapper<ProductModel, ProductFxModel, Long> {

    @Override
    ProductFxModel modelToFxModel(ProductModel model);

    @Override
    @InheritInverseConfiguration
    ProductModel fxModelToModel(ProductFxModel dto);
}
