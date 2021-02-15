package ir.comprehensive.mapper;

import ir.comprehensive.entity.Product;
import ir.comprehensive.model.ProductModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<Product, ProductModel> {

    @Override
    ProductModel entityToModel(Product entity);

    @Override
    @InheritInverseConfiguration
    Product modelToEntity(ProductModel dto);
}
