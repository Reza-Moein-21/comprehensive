package ir.comprehensive.database.test.service;

import ir.comprehensive.database.provider.mapper.MyNoteCategoryMapper;
import ir.comprehensive.database.provider.service.MyNoteCategoryDaoImpl;
import ir.comprehensive.database.service.MyNoteCategoryDao;
import ir.comprehensive.database.test.utils.DBExtension;
import ir.comprehensive.database.test.utils.Sql;
import ir.comprehensive.domain.enums.MyNoteCategoryStatusEnum;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
@Sql("/schema-init.sql")
@ExtendWith(DBExtension.class)
class MyNoteCategoryDaoImplTest {

    private final MyNoteCategoryDao myNoteCategoryDao;

    MyNoteCategoryDaoImplTest(DSLContext context, MyNoteCategoryMapper mapper) {
        this.myNoteCategoryDao = new MyNoteCategoryDaoImpl(context, mapper);
    }

    @Test
    void givingNullStatus_countByStatus_shouldGetZero() {
        assertThat(myNoteCategoryDao.countByStatus(null)).isEqualTo(0);
    }

    @Test
    void givingDone_countByStatus_shouldGetOne() {
        assertThat(myNoteCategoryDao.countByStatus(MyNoteCategoryStatusEnum.DONE)).isEqualTo(1);
    }

    @Test
    void givingNotPersistedStatus_countByStatus_shouldGetZero() {
        assertThat(myNoteCategoryDao.countByStatus(MyNoteCategoryStatusEnum.IN_PROGRESS)).isEqualTo(0);
    }

}