package ir.comprehensive.database.service.impl;

import ir.comprehensive.database.PersonEntity;
import ir.comprehensive.database.base.BaseServiceImpl;
import ir.comprehensive.database.mapper.PersonMapper;
import ir.comprehensive.database.model.PersonModel;
import ir.comprehensive.database.repository.PersonRepository;
import ir.comprehensive.database.service.PersonService;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl extends BaseServiceImpl<PersonEntity, PersonModel, PersonMapper, PersonRepository, Long> implements PersonService {

    public PersonServiceImpl(PersonMapper mapper, PersonRepository repository) {
        super(mapper, repository);
    }
}
