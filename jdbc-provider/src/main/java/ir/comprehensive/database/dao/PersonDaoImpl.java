package ir.comprehensive.database.dao;

import ir.comprehensive.database.dao.base.BaseDaoImpl;
import ir.comprehensive.database.entity.PersonEntity;
import ir.comprehensive.database.mapper.PersonMapper;
import ir.comprehensive.database.repository.PersonRepository;
import ir.comprehensive.domain.dao.PersonDao;
import ir.comprehensive.domain.model.PersonModel;
import org.springframework.stereotype.Service;

@Service
public class PersonDaoImpl extends BaseDaoImpl<PersonEntity, PersonModel, PersonMapper, PersonRepository, Long> implements PersonDao {

    public PersonDaoImpl(PersonMapper mapper, PersonRepository repository) {
        super(mapper, repository);
    }
}
