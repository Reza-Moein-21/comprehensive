package ir.comprehensive.database.test.service;

import ir.comprehensive.database.provider.config.JooqConfig;
import ir.comprehensive.database.provider.mapper.MyNoteMapperImpl;
import ir.comprehensive.database.provider.service.MyNoteDaoImpl;
import ir.comprehensive.database.service.MyNoteDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;


@Sql(scripts = {"classpath:schema-drop.sql", "classpath:schema-create.sql", "classpath:my-note-init.sql"})
@SpringJUnitConfig({MyNoteDaoImpl.class, MyNoteMapperImpl.class, JooqConfig.class})
class MyNoteDaoImplTest {

    @Autowired
    MyNoteDao myNoteDao;

    @Test
    void givingMyNoteCategoryIdAsNull_findNumberOfByDate_shouldReturnEmpty() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        var result = myNoteDao.findNumberOfByDate(startDate, endDate, null);
        assertThat(result).isEmpty();
    }


    @Test
    void givingInvalidMyNoteCategoryId_findNumberOfByDate_shouldReturnEmpty() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        var result = myNoteDao.findNumberOfByDate(startDate, endDate, -1L);
        assertThat(result).isEmpty();
    }

    @Test
    void givingValidMyNoteCategoryId_findNumberOfByDate_shouldGetExpectedMyNoteModel() {
        LocalDate startDate = LocalDate.of(2009, Month.DECEMBER, 15);
        LocalDate endDate = LocalDate.now();
        var result = myNoteDao.findNumberOfByDate(startDate, endDate, 987654321L);
        assertThat(result).hasSize(1);
        assertThat(result.get(0).creationTime()).isEqualTo(LocalDate.of(2009, Month.DECEMBER, 15));
    }

    @Test
    void givingOffStartDate_findNumberOfByDate_shouldGetEmpty() {
        LocalDate startDate = LocalDate.of(2009, Month.DECEMBER, 16);
        LocalDate endDate = LocalDate.now();
        var result = myNoteDao.findNumberOfByDate(startDate, endDate, 987654321L);
        assertThat(result).isEmpty();
    }

}