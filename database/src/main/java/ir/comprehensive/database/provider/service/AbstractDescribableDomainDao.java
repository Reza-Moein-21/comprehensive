package ir.comprehensive.database.provider.service;

import ir.comprehensive.database.exception.SearchingException;
import ir.comprehensive.database.model.PageModel;
import ir.comprehensive.database.model.PageRequestModel;
import ir.comprehensive.database.model.SearchCriteria;
import ir.comprehensive.database.provider.mapper.BaseMapper;
import ir.comprehensive.database.service.base.DescribableDomainDao;
import ir.comprehensive.domain.model.base.DescribableDomainModel;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Table;
import org.jooq.TableField;

import java.io.Serializable;

import static ir.comprehensive.database.model.SearchCriteria.Type.LIKE;

@Slf4j
public abstract class AbstractDescribableDomainDao<M extends DescribableDomainModel<I>, R extends Record, I extends Serializable>
        extends AbstractDomainDao<M, R, I>
        implements DescribableDomainDao<M, I> {
    public AbstractDescribableDomainDao(DSLContext context, BaseMapper<M, R> mapper, Table<R> table, TableField<R, I> idField) {
        super(context, mapper, table, idField);
    }

    @Override
    public PageModel<M> findAllByTitle(String title, PageRequestModel pageRequest) throws SearchingException {
        return search(pageRequest, SearchCriteria.of("title", LIKE, title));
    }

    @Override
    public PageModel<M> findAllByDescription(String description, PageRequestModel pageRequest) throws SearchingException {
        return search(pageRequest, SearchCriteria.of("description", LIKE, description));
    }
}
