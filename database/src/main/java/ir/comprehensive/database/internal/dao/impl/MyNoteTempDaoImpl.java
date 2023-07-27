package ir.comprehensive.database.internal.dao.impl;

import ir.comprehensive.database.internal.mapper.MyNoteTempMapper;
import ir.comprehensive.database.dao.MyNoteTempDao;
import ir.comprehensive.domain.model.MyNoteTempModel;
import org.jooq.DSLContext;
import org.jooq.generated.tables.records.MyNoteTempRecord;

import static org.jooq.generated.tables.MyNoteTemp.MY_NOTE_TEMP;

public class MyNoteTempDaoImpl extends AbstractDomainDao<MyNoteTempModel, MyNoteTempRecord, Long> implements MyNoteTempDao {
    public MyNoteTempDaoImpl(DSLContext context, MyNoteTempMapper mapper) {
        super(context, mapper, MY_NOTE_TEMP, MY_NOTE_TEMP.ID);
    }


}
