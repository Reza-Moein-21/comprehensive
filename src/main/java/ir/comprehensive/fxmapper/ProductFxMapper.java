package ir.comprehensive.fxmapper;

import ir.comprehensive.database.ProductEntity;
import ir.comprehensive.fxmodel.ProductFxModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProductFxMapper extends BaseFxMapper<ProductEntity, ProductFxModel> {

    @Override
    ProductFxModel entityToModel(ProductEntity entity);

    @Override
    @InheritInverseConfiguration
    ProductEntity modelToEntity(ProductFxModel dto);
}
