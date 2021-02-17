package ir.comprehensive.entity.service.impl;

import ir.comprehensive.entity.ProductEntity;
import ir.comprehensive.entity.base.BaseServiceImpl;
import ir.comprehensive.entity.mapper.ProductMapper;
import ir.comprehensive.entity.model.ProductModel;
import ir.comprehensive.entity.repository.ProductRepository;
import ir.comprehensive.entity.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductEntity, ProductModel, ProductMapper, ProductRepository, Long> implements ProductService {

    public ProductServiceImpl(ProductMapper mapper, ProductRepository repository) {
        super(mapper, repository);
    }
}
