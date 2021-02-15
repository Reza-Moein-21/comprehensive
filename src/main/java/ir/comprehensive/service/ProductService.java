package ir.comprehensive.service;

import ir.comprehensive.entity.Product;
import ir.comprehensive.mapper.ProductMapper;
import ir.comprehensive.fxmodel.ProductModel;
import ir.comprehensive.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService implements BaseService<Product, ProductModel> {
    private ProductRepository repository;
    private ProductMapper mapper;

    public ProductService(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<ProductModel> loadItem(ProductModel searchModel, PageRequest pageRequest) {
        Page<Product> page;
        page = repository.findAll(pageRequest);
        return page.map(mapper::entityToModel);
    }
}
