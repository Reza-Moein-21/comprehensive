package ir.comprehensive.database.test.service;

import ir.comprehensive.database.provider.mapper.ProductMapper;
import ir.comprehensive.database.provider.service.ProductDaoImpl;
import ir.comprehensive.database.service.ProductDao;
import ir.comprehensive.database.test.utils.DBExtension;
import ir.comprehensive.database.test.utils.Sql;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
@Sql("/schema-init.sql")
@ExtendWith(DBExtension.class)
public class ProductDaoImplTest {

    private final ProductDao productDao;

    public ProductDaoImplTest(DSLContext context, ProductMapper mapper) {
        this.productDao = new ProductDaoImpl(context, mapper);
    }

    @Test
    void initiationTest() {
        assertThat(productDao).isNotNull();
    }
}
