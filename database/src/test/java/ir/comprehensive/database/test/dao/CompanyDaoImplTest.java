package ir.comprehensive.database.test.dao;

import ir.comprehensive.database.internal.mapper.CompanyMapper;
import ir.comprehensive.database.internal.dao.impl.CompanyDaoImpl;
import ir.comprehensive.database.dao.CompanyDao;
import ir.comprehensive.database.test.utils.DBExtension;
import ir.comprehensive.database.test.utils.Sql;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
@Sql("/schema-init.sql")
@ExtendWith(DBExtension.class)
class CompanyDaoImplTest {

    private final CompanyDao companyDao;

    CompanyDaoImplTest(DSLContext context, CompanyMapper mapper) {
        this.companyDao = new CompanyDaoImpl(context, mapper);
    }

    @Test
    void initiationTest() {
        assertThat(companyDao).isNotNull();
    }
}