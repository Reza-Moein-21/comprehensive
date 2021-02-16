package ir.comprehensive.service;

import ir.comprehensive.entity.ProductEntity;
import ir.comprehensive.fxmapper.ProductFxMapper;
import ir.comprehensive.fxmodel.ProductFxModel;
import ir.comprehensive.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService implements BaseService<ProductEntity, ProductFxModel> {
    private ProductRepository repository;
    private ProductFxMapper mapper;

    public ProductService(ProductRepository repository, ProductFxMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<ProductFxModel> loadItem(ProductFxModel searchModel, PageRequest pageRequest) {
        Page<ProductEntity> page;
        page = repository.findAll(pageRequest);
        return page.map(mapper::entityToModel);
    }
}
