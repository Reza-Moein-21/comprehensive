package ir.comprehensive.database.dao;

import ir.comprehensive.database.dao.base.DescribableDomainDao;
import ir.comprehensive.domain.model.PersonModel;

import java.util.List;

public interface PersonDao extends DescribableDomainDao<PersonModel, Long> {
    List<PersonModel> findAllByName(String name);
}
