package ir.comprehensive.database.provider.service;

import ir.comprehensive.database.exception.DeletingException;
import ir.comprehensive.database.exception.PersistingException;
import ir.comprehensive.database.exception.SearchingException;
import ir.comprehensive.database.model.PageModel;
import ir.comprehensive.database.model.PageRequestModel;
import ir.comprehensive.database.model.SaveStrategy;
import ir.comprehensive.database.model.SearchCriteria;
import ir.comprehensive.database.provider.mapper.PersonMapper;
import ir.comprehensive.database.service.PersonDao;
import ir.comprehensive.domain.model.PersonModel;
import org.jooq.DSLContext;
import org.jooq.generated.tables.records.PersonRecord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.jooq.generated.tables.Person.PERSON;

@Service
public class PersonDaoImpl extends AbstractDomainDao<PersonModel, PersonRecord, Long> implements PersonDao {

    private final DSLContext context;
    private final PersonMapper mapper;

    public PersonDaoImpl(DSLContext context, PersonMapper mapper) {
        super(context, mapper, PERSON);
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

    @Override
    public List<PersonModel> search(List<SearchCriteria> criteriaList) throws SearchingException {
        return null;
    }

    @Override
    public PageModel<PersonModel> search(PageRequestModel pageRequest, List<SearchCriteria> criteriaList) throws SearchingException {
        return null;
    }


    @Override
    public Optional<PersonModel> findById(Long id) throws SearchingException {
        return Optional.ofNullable(context.fetchOne(PERSON, PERSON.ID.eq(id))).map(this.mapper::recordToModel);
    }

    @Override
    public int delete(PersonModel model) throws DeletingException {
        return deleteExecutionWrapper(() -> context.deleteFrom(PERSON).where(PERSON.ID.eq(model.getId())));

    }

    @Override
    public int deleteAll(Set<Long> ids) throws DeletingException {
        return deleteExecutionWrapper(() -> context.deleteFrom(PERSON).where(PERSON.ID.in(ids)));

    }

    @Override
    public int deleteById(Long id) throws DeletingException {
        return deleteExecutionWrapper(() -> context.deleteFrom(PERSON).where(PERSON.ID.in(id)));
    }

    @Override
    public PersonModel save(PersonModel model) throws PersistingException {
        return null;
    }

    @Override
    public PersonModel save(PersonModel model, SaveStrategy strategy) throws PersistingException {
        return null;
    }
}
