package ir.comprehensive.database.provider.service;

import ir.comprehensive.database.provider.mapper.MyNoteTempMapper;
import ir.comprehensive.database.service.MyNoteTempDao;
import ir.comprehensive.domain.model.MyNoteTempModel;
import org.jooq.DSLContext;
import org.jooq.generated.tables.records.MyNoteTempRecord;
import org.springframework.stereotype.Service;

import static org.jooq.generated.tables.MyNoteTemp.MY_NOTE_TEMP;

@Service
public class MyNoteTempDaoImpl extends AbstractDomainDao<MyNoteTempModel, MyNoteTempRecord, Long> implements MyNoteTempDao {
    public MyNoteTempDaoImpl(DSLContext context, MyNoteTempMapper mapper) {
        super(context, mapper, MY_NOTE_TEMP, MY_NOTE_TEMP.ID);
    }


}
