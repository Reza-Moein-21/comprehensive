package ir.comprehensive.entity.service.impl;

import ir.comprehensive.entity.PersonEntity;
import ir.comprehensive.entity.base.BaseServiceImpl;
import ir.comprehensive.entity.mapper.PersonMapper;
import ir.comprehensive.entity.model.PersonModel;
import ir.comprehensive.entity.repository.PersonRepository;
import ir.comprehensive.entity.service.PersonService;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl extends BaseServiceImpl<PersonEntity, PersonModel, PersonMapper, PersonRepository, Long> implements PersonService {

    public PersonServiceImpl(PersonMapper mapper, PersonRepository repository) {
        super(mapper, repository);
    }
}
