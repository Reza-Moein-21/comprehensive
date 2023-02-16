package ir.comprehensive.database.service;

import ir.comprehensive.database.service.base.DescribableDomainDao;
import ir.comprehensive.domain.model.PersonModel;

import java.util.List;

public interface PersonDao extends DescribableDomainDao<PersonModel, Long> {
    List<PersonModel> findAllByName(String name);
}
