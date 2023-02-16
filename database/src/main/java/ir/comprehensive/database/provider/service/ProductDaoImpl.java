package ir.comprehensive.database.provider.service;

import ir.comprehensive.database.provider.mapper.ProductMapper;
import ir.comprehensive.database.service.ProductDao;
import ir.comprehensive.domain.model.ProductModel;
import org.jooq.DSLContext;
import org.jooq.generated.tables.records.ProductRecord;
import org.springframework.stereotype.Service;

import static org.jooq.generated.tables.Product.PRODUCT;

@Service
public class ProductDaoImpl extends AbstractDomainDao<ProductModel, ProductRecord, Long> implements ProductDao {
    public ProductDaoImpl(DSLContext context, ProductMapper mapper) {
        super(context, mapper, PRODUCT, PRODUCT.ID);
    }

}
