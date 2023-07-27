package ir.comprehensive.database.internal.dao.impl;

import ir.comprehensive.database.internal.mapper.MyNoteCategoryMapper;
import ir.comprehensive.database.dao.MyNoteCategoryDao;
import ir.comprehensive.domain.enums.MyNoteCategoryStatusEnum;
import ir.comprehensive.domain.model.MyNoteCategoryModel;
import org.jooq.DSLContext;
import org.jooq.generated.tables.records.MyNoteCategoryRecord;

import static org.jooq.generated.tables.MyNoteCategory.MY_NOTE_CATEGORY;

public class MyNoteCategoryDaoImpl extends AbstractDescribableDomainDao<MyNoteCategoryModel, MyNoteCategoryRecord, Long> implements MyNoteCategoryDao {
    private final DSLContext context;

    public MyNoteCategoryDaoImpl(DSLContext context, MyNoteCategoryMapper mapper) {
        super(context, mapper, MY_NOTE_CATEGORY, MY_NOTE_CATEGORY.ID);
        this.context = context;
    }


    @Override
    public long countByStatus(MyNoteCategoryStatusEnum status) {
        if (status == null) return 0;
        return context.fetchCount(MY_NOTE_CATEGORY, MY_NOTE_CATEGORY.STATUS.eq(status.toString()));
    }
}
