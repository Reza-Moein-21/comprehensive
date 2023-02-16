package ir.comprehensive.database.service.base;


import ir.comprehensive.database.exception.SearchingException;
import ir.comprehensive.database.model.PageModel;
import ir.comprehensive.database.model.PageRequestModel;
import ir.comprehensive.domain.model.base.DescribableDomainModel;

import java.io.Serializable;

public interface DescribableDomainDao<M extends DescribableDomainModel<I>, I extends Serializable> {
    PageModel<M> findAllByTitle(String title, PageRequestModel pageRequest) throws SearchingException;

    PageModel<M> findAllByDescription(String description, PageRequestModel pageRequest) throws SearchingException;
}
