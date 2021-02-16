package ir.comprehensive.entity.mapper;

import ir.comprehensive.entity.ProductEntity;
import ir.comprehensive.entity.base.BaseMapper;
import ir.comprehensive.entity.model.ProductModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<ProductEntity, ProductModel, Long> {
}
