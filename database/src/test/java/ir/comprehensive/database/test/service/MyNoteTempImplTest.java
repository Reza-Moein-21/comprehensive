package ir.comprehensive.database.test.service;

import ir.comprehensive.database.provider.mapper.MyNoteTempMapper;
import ir.comprehensive.database.provider.service.MyNoteTempDaoImpl;
import ir.comprehensive.database.service.MyNoteTempDao;
import ir.comprehensive.database.test.utils.DBExtension;
import ir.comprehensive.database.test.utils.Sql;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
@Sql("/schema-init.sql")
@ExtendWith(DBExtension.class)
class MyNoteTempImplTest {

    private final MyNoteTempDao myNoteTempDao;

    public MyNoteTempImplTest(DSLContext context, MyNoteTempMapper mapper) {
        this.myNoteTempDao = new MyNoteTempDaoImpl(context, mapper);
    }

    @Test
    void initiationTest() {
        assertThat(myNoteTempDao).isNotNull();
    }
}