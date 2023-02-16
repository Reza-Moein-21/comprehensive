package ir.comprehensive.database.provider.service;

import ir.comprehensive.database.provider.mapper.MyNoteMapper;
import ir.comprehensive.database.service.MyNoteDao;
import ir.comprehensive.domain.model.MyNoteModel;
import ir.comprehensive.domain.model.dto.CalenderNoteStatus;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.generated.tables.records.MyNoteRecord;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.jooq.generated.tables.MyNote.MY_NOTE;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.selectCount;

@Slf4j
@Service
public class MyNoteDaoImpl extends AbstractDescribableDomainDao<MyNoteModel, MyNoteRecord, Long> implements MyNoteDao {
    private final DSLContext context;

    public MyNoteDaoImpl(DSLContext context, MyNoteMapper mapper) {
        super(context, mapper, MY_NOTE, MY_NOTE.ID);
        this.context = context;
    }


    @Override
    public List<CalenderNoteStatus> findNumberOfByDate(LocalDate startDate, LocalDate endDate, Long myNoteCategoryId) {
        if (myNoteCategoryId == null) return Collections.emptyList();

        var note1 = MY_NOTE.as("note1");
        return context.select(MY_NOTE.CREATION_DATE,
                        field(selectCount()
                                .from(note1)
                                .where(note1.CREATION_DATE.eq(MY_NOTE.CREATION_DATE))
                                .and(note1.IS_ACTIVE.eq(true))
                                .and(note1.MY_NOTE_CATEGORY_ID.eq(myNoteCategoryId)))
                        , field(selectCount()
                                .from(note1)
                                .where(note1.CREATION_DATE.eq(MY_NOTE.CREATION_DATE))
                                .and(note1.IS_ACTIVE.eq(false))
                                .and(note1.MY_NOTE_CATEGORY_ID.eq(myNoteCategoryId)))
                ).from(MY_NOTE)
                .where(MY_NOTE.CREATION_DATE.between(startDate, endDate))
                .and(MY_NOTE.MY_NOTE_CATEGORY_ID.eq(myNoteCategoryId))
                .groupBy(MY_NOTE.CREATION_DATE)
                .fetch()
                .map(record -> new CalenderNoteStatus(record.component1(), record.component2(), record.component3()));
    }
}
