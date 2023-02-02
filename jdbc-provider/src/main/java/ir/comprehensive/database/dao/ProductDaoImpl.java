package ir.comprehensive.database.dao;

import ir.comprehensive.database.dao.base.BaseDaoImpl;
import ir.comprehensive.database.entity.ProductEntity;
import ir.comprehensive.database.mapper.ProductMapper;
import ir.comprehensive.database.repository.ProductRepository;
import ir.comprehensive.domain.dao.ProductDao;
import ir.comprehensive.domain.model.ProductModel;
import org.springframework.stereotype.Service;

@Service
public class ProductDaoImpl extends BaseDaoImpl<ProductEntity, ProductModel, ProductMapper, ProductRepository, Long> implements ProductDao {

    public ProductDaoImpl(ProductMapper mapper, ProductRepository repository) {
        super(mapper, repository);
    }
}
