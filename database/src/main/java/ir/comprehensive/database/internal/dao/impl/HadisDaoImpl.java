package ir.comprehensive.database.internal.dao.impl;

import ir.comprehensive.database.internal.mapper.HadisMapper;
import ir.comprehensive.database.dao.HadisDao;
import ir.comprehensive.domain.model.HadisModel;
import org.jooq.DSLContext;
import org.jooq.generated.tables.records.HadisRecord;

import static org.jooq.generated.tables.Hadis.HADIS;

public class HadisDaoImpl extends AbstractDescribableDomainDao<HadisModel, HadisRecord, Long> implements HadisDao {

    public HadisDaoImpl(DSLContext context, HadisMapper mapper) {
        super(context, mapper, HADIS, HADIS.ID);
    }
}
