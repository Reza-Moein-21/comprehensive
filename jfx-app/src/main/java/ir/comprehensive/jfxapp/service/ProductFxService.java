package ir.comprehensive.jfxapp.service;

import ir.comprehensive.database.entity.ProductEntity;
import ir.comprehensive.fxmapper.ProductFxMapper;
import ir.comprehensive.fxmodel.ProductFxModel;
import ir.comprehensive.repository.ProductFxRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductFxService implements BaseFxService<ProductEntity, ProductFxModel> {
    private ProductFxRepository repository;
    private ProductFxMapper mapper;

    public ProductFxService(ProductFxRepository repository, ProductFxMapper mapper) {
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
