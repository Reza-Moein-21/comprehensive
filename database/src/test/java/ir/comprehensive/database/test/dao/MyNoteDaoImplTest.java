package ir.comprehensive.database.test.dao;

import ir.comprehensive.database.internal.mapper.MyNoteMapper;
import ir.comprehensive.database.internal.dao.impl.MyNoteDaoImpl;
import ir.comprehensive.database.dao.MyNoteDao;
import ir.comprehensive.database.test.utils.DBExtension;
import ir.comprehensive.database.test.utils.Sql;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
@Sql("/my-note-init.sql")
@ExtendWith(DBExtension.class)
class MyNoteDaoImplTest {

    private final MyNoteDao myNoteDao;

    public MyNoteDaoImplTest(DSLContext context, MyNoteMapper mapper) {
        this.myNoteDao = new MyNoteDaoImpl(context, mapper);
    }

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