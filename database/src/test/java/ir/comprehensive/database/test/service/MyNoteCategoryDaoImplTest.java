package ir.comprehensive.database.test.service;

import ir.comprehensive.database.provider.config.JooqConfig;
import ir.comprehensive.database.provider.mapper.MyNoteCategoryMapperImpl;
import ir.comprehensive.database.provider.service.MyNoteCategoryDaoImpl;
import ir.comprehensive.database.service.MyNoteCategoryDao;
import ir.comprehensive.domain.enums.MyNoteCategoryStatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;


@Sql(scripts = {"classpath:schema-drop.sql", "classpath:schema-create.sql", "classpath:schema-init.sql"})
@SpringJUnitConfig({MyNoteCategoryDaoImpl.class, MyNoteCategoryMapperImpl.class, JooqConfig.class})
class MyNoteCategoryDaoImplTest {

    @Autowired
    MyNoteCategoryDao myNoteCategoryDao;

    @Test
    void givingNullStatus_countByStatus_shouldGetZero() {
        assertThat(myNoteCategoryDao.countByStatus(null)).isEqualTo(0);
    }
    @Test
    void givingDone_countByStatus_shouldGetOne() {
        assertThat(myNoteCategoryDao.countByStatus(MyNoteCategoryStatusEnum.DONE)).isEqualTo(1);
    }    @Test
    void givingNotPersistedStatus_countByStatus_shouldGetZero() {
        assertThat(myNoteCategoryDao.countByStatus(MyNoteCategoryStatusEnum.IN_PROGRESS)).isEqualTo(0);
    }

}