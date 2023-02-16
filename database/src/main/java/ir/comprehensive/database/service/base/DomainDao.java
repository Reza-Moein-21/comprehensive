package ir.comprehensive.database.service.base;


import ir.comprehensive.database.exception.DeletingException;
import ir.comprehensive.database.exception.PersistingException;
import ir.comprehensive.database.exception.SearchingException;
import ir.comprehensive.database.model.PageModel;
import ir.comprehensive.database.model.PageRequestModel;
import ir.comprehensive.database.model.SaveStrategy;
import ir.comprehensive.database.model.SearchCriteria;
import ir.comprehensive.domain.model.base.DomainModel;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DomainDao<M extends DomainModel<I>, I extends Serializable> {
    List<M> search(SearchCriteria... searchCriteria) throws SearchingException;

    PageModel<M> search(PageRequestModel pageRequest, SearchCriteria... searchCriteria) throws SearchingException;

    PageModel<M> findAll(PageRequestModel pageRequest) throws SearchingException;

    int totalCount() throws SearchingException;

    Optional<M> findById(I id) throws SearchingException;

    int delete(M model) throws DeletingException;

    int deleteAll(Set<I> ids) throws DeletingException;

    int deleteById(I id) throws DeletingException;

    M save(M model) throws PersistingException;

    M save(M model, SaveStrategy strategy) throws PersistingException;
}
