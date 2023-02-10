package ir.comprehensive.database.provider.service;

import ir.comprehensive.database.provider.mapper.PersonMapper;
import ir.comprehensive.database.service.PersonDao;
import ir.comprehensive.domain.model.PersonModel;
import org.jooq.DSLContext;
import org.jooq.generated.tables.records.PersonRecord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.generated.tables.Person.PERSON;

@Service
public class PersonDaoImpl extends AbstractDomainDao<PersonModel, PersonRecord, Long> implements PersonDao {

    private final DSLContext context;
    private final PersonMapper mapper;

    public PersonDaoImpl(DSLContext context, PersonMapper mapper) {
        super(context, mapper, PERSON, PERSON.ID);
        this.context = context;
        this.mapper = mapper;
    }

    @Override
    public List<PersonModel> findAllByName(String name) {
        return context.selectFrom(PERSON)
                .where(PERSON.FIRST_NAME.concat(" ").concat(PERSON.LAST_NAME)
                        .likeIgnoreCase("%" + name + "%"))
                .fetch()
                .stream()
                .map(mapper::recordToModel)
                .collect(Collectors.toList());
    }

}
