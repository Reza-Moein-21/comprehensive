package ir.comprehensive.database.internal.dao.impl;

import ir.comprehensive.database.internal.mapper.ProductMapper;
import ir.comprehensive.database.dao.ProductDao;
import ir.comprehensive.domain.model.ProductModel;
import org.jooq.DSLContext;
import org.jooq.generated.tables.records.ProductRecord;

import static org.jooq.generated.tables.Product.PRODUCT;

public class ProductDaoImpl extends AbstractDescribableDomainDao<ProductModel, ProductRecord, Long> implements ProductDao {
    public ProductDaoImpl(DSLContext context, ProductMapper mapper) {
        super(context, mapper, PRODUCT, PRODUCT.ID);
    }

}
