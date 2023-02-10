package ir.comprehensive.database.provider.service;

import ir.comprehensive.database.provider.mapper.CategoryMapper;
import ir.comprehensive.database.service.CategoryDao;
import ir.comprehensive.domain.model.CategoryModel;
import org.jooq.DSLContext;
import org.jooq.generated.tables.records.CategoryRecord;
import org.springframework.stereotype.Service;

import static org.jooq.generated.tables.Category.CATEGORY;

@Service
public class CategoryDaoImpl extends AbstractDomainDao<CategoryModel, CategoryRecord, Long> implements CategoryDao {

    public CategoryDaoImpl(DSLContext context, CategoryMapper mapper) {
        super(context, mapper, CATEGORY, CATEGORY.ID);
    }
}
