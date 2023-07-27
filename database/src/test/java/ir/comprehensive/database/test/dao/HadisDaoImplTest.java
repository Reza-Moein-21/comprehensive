package ir.comprehensive.database.test.dao;

import ir.comprehensive.database.internal.mapper.HadisMapper;
import ir.comprehensive.database.internal.dao.impl.HadisDaoImpl;
import ir.comprehensive.database.dao.HadisDao;
import ir.comprehensive.database.test.utils.DBExtension;
import ir.comprehensive.database.test.utils.Sql;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
@Sql("/schema-init.sql")
@ExtendWith(DBExtension.class)
class HadisDaoImplTest {

    private final HadisDao categoryDao;

    HadisDaoImplTest(DSLContext context, HadisMapper mapper) {
        this.categoryDao = new HadisDaoImpl(context, mapper);
    }

    @Test
    void initiationTest() {
        assertThat(categoryDao).isNotNull();
    }
}