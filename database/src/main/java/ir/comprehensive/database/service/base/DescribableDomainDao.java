package ir.comprehensive.database.service.base;

import ir.comprehensive.database.model.SearchCriteria;
import ir.comprehensive.domain.model.base.DescribableDomainModel;

import java.io.Serializable;
import java.util.List;

public interface DescribableDomainDao<M extends DescribableDomainModel<I>, I extends Serializable> extends DomainDao<M, I> {
    default List<M> findAllByTitle(String title) {
        return search(List.of(SearchCriteria.ofIgnoreNull("title", SearchCriteria.Type.LIKE, title)));
    }
}
