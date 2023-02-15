package ir.comprehensive.database.test.service;

import ir.comprehensive.database.provider.config.JooqConfig;
import ir.comprehensive.database.provider.mapper.MyNoteTempMapperImpl;
import ir.comprehensive.database.provider.service.MyNoteTempDaoImpl;
import ir.comprehensive.database.service.MyNoteTempDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;


@Sql(scripts = {"classpath:schema-drop.sql", "classpath:schema-create.sql", "classpath:schema-init.sql"})
@SpringJUnitConfig({MyNoteTempDaoImpl.class, MyNoteTempMapperImpl.class, JooqConfig.class})
class MyNoteTempImplTest {

    @Autowired
    MyNoteTempDao myNoteTempDao;

    @Test
    void initiationTest() {
        assertThat(myNoteTempDao).isNotNull();
    }
}