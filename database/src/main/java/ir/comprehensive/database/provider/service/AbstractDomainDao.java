package ir.comprehensive.database.provider.service;

import ir.comprehensive.database.exception.DeletingException;
import ir.comprehensive.database.exception.SearchingException;
import ir.comprehensive.database.model.PageModel;
import ir.comprehensive.database.model.PageRequestModel;
import ir.comprehensive.database.provider.mapper.BaseMapper;
import ir.comprehensive.database.service.base.DomainDao;
import ir.comprehensive.domain.model.base.DomainModel;
import lombok.RequiredArgsConstructor;
import org.h2.api.ErrorCode;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.exception.DataAccessException;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractDomainDao<M extends DomainModel<I>, R extends Record, I extends Serializable> implements DomainDao<M, I> {
    private final DSLContext context;
    private final BaseMapper<M, R> mapper;
    private final Table<R> table;
    private final TableField<R, I> idField;

    @Override
    public PageModel<M> findAll(PageRequestModel pageRequest) throws SearchingException {

        if (pageRequest.page() < 1) return PageModel.ofEmpty();

        var offset = (pageRequest.page() - 1) * pageRequest.size();
        var limit = pageRequest.size();

        var currentPage = pageRequest.page();
        var totalRecords = this.totalCount();
        int totalPage = (int) Math.ceil(((double) totalRecords / (double) pageRequest.size()));

        var limitedSelectQuery = context.selectFrom(this.table)
                .offset(offset)
                .limit(limit)
                .fetch()
                .stream()
                .map(this.mapper::recordToModel)
                .collect(Collectors.toList());

        int numberOfElements = limitedSelectQuery.size();

        return new PageModel<>(currentPage, totalRecords, totalPage, numberOfElements, limitedSelectQuery);
    }

    @Override
    public long totalCount() throws SearchingException {
        return context.fetchCount(this.table);
    }

    @Override
    public Optional<M> findById(I id) throws SearchingException {
        return Optional.ofNullable(context.fetchOne(this.table, this.idField.eq(id))).map(this.mapper::recordToModel);
    }

    @Override
    public int delete(M model) throws DeletingException {
        return deleteExecutionWrapper(() -> context.deleteFrom(this.table).where(this.idField.eq(model.getId())));

    }

    @Override
    public int deleteAll(Set<I> ids) throws DeletingException {
        return deleteExecutionWrapper(() -> context.deleteFrom(this.table).where(this.idField.in(ids)));

    }

    @Override
    public int deleteById(I id) throws DeletingException {
        return deleteExecutionWrapper(() -> context.deleteFrom(this.table).where(this.idField.equal(id)));
    }


    protected int deleteExecutionWrapper(Supplier<Query> deletingQuerySupplier) throws DeletingException {
        try {
            return deletingQuerySupplier.get().execute();
        } catch (DataAccessException e) {
            if (e.sqlState().equals(String.valueOf(ErrorCode.REFERENTIAL_INTEGRITY_VIOLATED_CHILD_EXISTS_1)))
                throw new DeletingException(e.sqlState(), "Referential integrity violated, child exists");

            throw new DeletingException(e.sqlState(), e.getMessage());
        }
    }

}
