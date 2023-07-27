package ir.comprehensive.database.internal.dao.impl;

import ir.comprehensive.database.internal.mapper.CompanyMapper;
import ir.comprehensive.database.dao.CompanyDao;
import ir.comprehensive.domain.model.CompanyModel;
import org.jooq.DSLContext;
import org.jooq.generated.tables.records.CategoryRecord;

import static org.jooq.generated.tables.Category.CATEGORY;

public class CompanyDaoImpl extends AbstractDescribableDomainDao<CompanyModel, CategoryRecord, Long> implements CompanyDao {

    public CompanyDaoImpl(DSLContext context, CompanyMapper mapper) {
        super(context, mapper, CATEGORY, CATEGORY.ID);
    }
}
