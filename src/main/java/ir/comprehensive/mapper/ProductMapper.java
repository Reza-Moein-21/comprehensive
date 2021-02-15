package ir.comprehensive.mapper;

import ir.comprehensive.entity.ProductEntity;
import ir.comprehensive.fxmodel.ProductModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<ProductEntity, ProductModel> {

    @Override
    ProductModel entityToModel(ProductEntity entity);

    @Override
    @InheritInverseConfiguration
    ProductEntity modelToEntity(ProductModel dto);
}
