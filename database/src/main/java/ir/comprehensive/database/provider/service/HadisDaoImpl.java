package ir.comprehensive.database.provider.service;

import ir.comprehensive.database.provider.mapper.HadisMapper;
import ir.comprehensive.database.service.HadisDao;
import ir.comprehensive.domain.model.HadisModel;
import org.jooq.DSLContext;
import org.jooq.generated.tables.records.HadisRecord;

import static org.jooq.generated.tables.Hadis.HADIS;

public class HadisDaoImpl extends AbstractDescribableDomainDao<HadisModel, HadisRecord, Long> implements HadisDao {

    public HadisDaoImpl(DSLContext context, HadisMapper mapper) {
        super(context, mapper, HADIS, HADIS.ID);
    }
}
