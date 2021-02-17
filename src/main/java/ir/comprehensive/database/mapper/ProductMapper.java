package ir.comprehensive.database.mapper;

import ir.comprehensive.database.ProductEntity;
import ir.comprehensive.database.base.BaseMapper;
import ir.comprehensive.database.model.ProductModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<ProductEntity, ProductModel, Long> {
}
