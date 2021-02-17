package ir.comprehensive.database.service.impl;

import ir.comprehensive.database.entity.ProductEntity;
import ir.comprehensive.database.base.BaseServiceImpl;
import ir.comprehensive.database.mapper.ProductMapper;
import ir.comprehensive.database.model.ProductModel;
import ir.comprehensive.database.repository.ProductRepository;
import ir.comprehensive.database.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductEntity, ProductModel, ProductMapper, ProductRepository, Long> implements ProductService {

    public ProductServiceImpl(ProductMapper mapper, ProductRepository repository) {
        super(mapper, repository);
    }
}
