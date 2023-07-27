package ir.comprehensive.database.test.dao;

import ir.comprehensive.database.internal.mapper.PersonMapper;
import ir.comprehensive.database.internal.dao.impl.PersonDaoImpl;
import ir.comprehensive.database.dao.PersonDao;
import ir.comprehensive.database.test.utils.DBExtension;
import ir.comprehensive.database.test.utils.Sql;
import org.apache.commons.lang3.StringUtils;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("/schema-init.sql")
@ExtendWith(DBExtension.class)
class PersonDaoImplTest {

    private final PersonDao personDao;

    public PersonDaoImplTest(DSLContext context, PersonMapper mapper) {
        this.personDao = new PersonDaoImpl(context, mapper);
    }

    @Test
    void givingValidPersonRecord_findAllByName_shouldGetOneExpectedResult() {
        final String searchQuery = "علی علی";
        var persons = personDao.findAllByName(searchQuery);

        assertThat(persons).hasSize(1);
        assertThat(persons.get(0))
                .matches(p -> StringUtils.containsAny(p.firstName(), searchQuery) ||
                        StringUtils.containsAny(p.lastName(), searchQuery));

    }

}