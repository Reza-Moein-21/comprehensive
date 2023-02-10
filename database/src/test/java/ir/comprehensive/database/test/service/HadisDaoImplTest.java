package ir.comprehensive.database.test.service;

import ir.comprehensive.database.provider.config.JooqConfig;
import ir.comprehensive.database.provider.mapper.HadisMapperImpl;
import ir.comprehensive.database.provider.service.HadisDaoImpl;
import ir.comprehensive.database.service.HadisDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;


@Sql(scripts = {"classpath:schema-drop.sql", "classpath:schema-create.sql", "classpath:schema-init.sql"})
@SpringJUnitConfig({HadisDaoImpl.class, HadisMapperImpl.class, JooqConfig.class})
class HadisDaoImplTest {

    @Autowired
    HadisDao categoryDao;

    @Test
    void initiationTest() {
        assertThat(categoryDao).isNotNull();
    }
}