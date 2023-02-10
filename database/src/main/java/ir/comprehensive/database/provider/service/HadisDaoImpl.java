package ir.comprehensive.database.provider.service;

import ir.comprehensive.database.provider.mapper.HadisMapper;
import ir.comprehensive.database.service.HadisDao;
import ir.comprehensive.domain.model.HadisModel;
import org.jooq.DSLContext;
import org.jooq.generated.tables.records.HadisRecord;
import org.springframework.stereotype.Service;

import static org.jooq.generated.tables.Hadis.HADIS;

@Service
public class HadisDaoImpl extends AbstractDomainDao<HadisModel, HadisRecord, Long> implements HadisDao {

    public HadisDaoImpl(DSLContext context, HadisMapper mapper) {
        super(context, mapper, HADIS, HADIS.ID);
    }
}
