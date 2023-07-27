package ir.comprehensive.database.dao;

import ir.comprehensive.database.exception.SearchingException;
import ir.comprehensive.database.model.SearchCriteria;
import ir.comprehensive.database.dao.base.DescribableDomainDao;
import ir.comprehensive.domain.model.WarehouseTagModel;

import java.util.List;

import static ir.comprehensive.database.model.SearchCriteria.Type.EQUALS;

public interface WarehouseTagDao extends DescribableDomainDao<WarehouseTagModel, Long> {
    default List<WarehouseTagModel> findByTitleExact(String title) throws SearchingException {
        return search(SearchCriteria.of("title", EQUALS, title));
    }

}
