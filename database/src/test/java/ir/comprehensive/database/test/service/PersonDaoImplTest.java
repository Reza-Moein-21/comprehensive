package ir.comprehensive.database.test.service;

import ir.comprehensive.database.provider.config.JooqConfig;
import ir.comprehensive.database.provider.mapper.PersonMapperImpl;
import ir.comprehensive.database.provider.service.PersonDaoImpl;
import ir.comprehensive.database.service.PersonDao;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;


@Sql(scripts = {"classpath:schema-drop.sql", "classpath:schema-create.sql", "classpath:schema-init.sql"})
@SpringJUnitConfig({PersonDaoImpl.class, PersonMapperImpl.class, JooqConfig.class})
class PersonDaoImplTest {

    @Autowired
    PersonDao personDao;

    @Test
    void givingValidPersonRecord_findAllByName_shouldGetOneExpectedResult() {
        final String searchQuery = "علی علی";
        var persons = personDao.findAllByName(searchQuery);

        assertThat(persons).hasSize(1);
        assertThat(persons.get(0))
                .matches(p -> StringUtils.containsAny(p.getFirstName(), searchQuery) ||
                        StringUtils.containsAny(p.getLastName(), searchQuery));

    }

}