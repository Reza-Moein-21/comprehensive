package ir.comprehensive.database.service;

import ir.comprehensive.database.service.base.DomainDao;
import ir.comprehensive.domain.model.PersonModel;

import java.util.List;

public interface PersonDao extends DomainDao<PersonModel, Long> {
    List<PersonModel> findAllByName(String name);
}
