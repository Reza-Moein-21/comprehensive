package ir.comprehensive.database.test.service;

import ir.comprehensive.database.exception.DeletingException;
import ir.comprehensive.database.model.PageModel;
import ir.comprehensive.database.model.PageRequestModel;
import ir.comprehensive.database.provider.config.JooqConfig;
import ir.comprehensive.database.provider.mapper.PersonMapperImpl;
import ir.comprehensive.database.provider.service.PersonDaoImpl;
import ir.comprehensive.database.service.PersonDao;
import ir.comprehensive.domain.exception.ExceptionTemplate;
import ir.comprehensive.domain.model.PersonModel;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


@Sql(scripts = {"classpath:schema-drop.sql", "classpath:schema-create.sql", "classpath:schema-init.sql"})
@SpringJUnitConfig({PersonDaoImpl.class, PersonMapperImpl.class, JooqConfig.class})
class PersonDaoImplTest {

    @Autowired
    PersonDao personDao;

    @Nested
    class PersonDaoImplDMLTest {

        @Test
        void givingTwoPersonRecordWithNoRelation_deleteAll_shouldDeleteTowRecord() {
            int numberOfDeleted = personDao.deleteAll(Set.of(987654221L, 678954321L));
            assertThat(numberOfDeleted).isEqualTo(2);
            Optional<PersonModel> deletedPerson = personDao.findById(987654221L);
            assertThat(deletedPerson).isNotPresent();
        }

        @Test
        void givingOnePersonRecordWithNoRelation_deleteOne_shouldDeleteOneRecord() {
            int numberOfDeleted = personDao.deleteById(987654221L);
            assertThat(numberOfDeleted).isEqualTo(1);
            Optional<PersonModel> deletedPerson = personDao.findById(987654221L);
            assertThat(deletedPerson).isNotPresent();
        }

        @Test
        void givingPersonRecordWithRelation_deleteOne_shouldTrowDeletingException() {
            var expectedId = 987654321L;
            assertThatThrownBy(() -> personDao.deleteById(expectedId))
                    .isInstanceOf(ExceptionTemplate.class)
                    .isInstanceOf(DeletingException.class)
                    .matches(t -> ((ExceptionTemplate) t).getCode().equals("23503"));

            var notDeletedPerson = personDao.findById(expectedId);
            assertThat(notDeletedPerson).isPresent();
        }

        @Test
        void givingPersonModelWithRelation_deleteOne_shouldTrowDeletingException() {
            var expectedId = 987654321L;
            var model = new PersonModel();
            model.setId(expectedId);
            assertThatThrownBy(() -> personDao.delete(model))
                    .isInstanceOf(ExceptionTemplate.class)
                    .isInstanceOf(DeletingException.class)
                    .matches(t -> ((ExceptionTemplate) t).getCode().equals("23503"));

            var notDeletedPerson = personDao.findById(expectedId);
            assertThat(notDeletedPerson).isPresent();
        }

        @Test
        void givingMoreThenOnePersonRecordWithRelation_deleteAll_shouldTrowDeletingException() {
            var recordIdOne = 987612345L;
            var recordIdTwo = 934543526L;
            assertThatThrownBy(() -> personDao.deleteAll(Set.of(recordIdOne, recordIdTwo)))
                    .isInstanceOf(ExceptionTemplate.class)
                    .isInstanceOf(DeletingException.class)
                    .matches(t -> ((ExceptionTemplate) t).getCode().equals("23503"));

            var notDeletedPerson = personDao.findById(recordIdOne);
            assertThat(notDeletedPerson).isPresent();

            notDeletedPerson = personDao.findById(recordIdTwo);
            assertThat(notDeletedPerson).isPresent();
        }
    }

    @Test
    void givingValidPersonRecord_findAllByName_shouldGetOneExpectedResult() {
        final String searchQuery = "علی علی";
        var persons = personDao.findAllByName(searchQuery);

        assertThat(persons).hasSize(1);
        assertThat(persons.get(0))
                .matches(p -> StringUtils.containsAny(p.getFirstName(), searchQuery) ||
                        StringUtils.containsAny(p.getLastName(), searchQuery));

    }

    @Test
    void giving10Record_totalCount_shouldReturn10() {
        assertThat(personDao.totalCount()).isEqualTo(10);
    }

    @Test
    void givingValidPersonRecord_findById_ShouldGetExpectedRecord() {
        var expectedId = 987654321L;
        var personOptional = personDao.findById(expectedId);
        assertThat(personOptional)
                .isPresent()
                .get()
                .matches(p -> p.getId().equals(expectedId));
    }

    @Test
    void givingPageSizeMoreThenTotalRecord_findAll_totalPageShouldBeOne() {
        var totalRecords = (int) personDao.totalCount();
        var pageSizeMoreThenTotalRecords = (totalRecords * 10);
        PageModel<PersonModel> pageModel = personDao.findAll(PageRequestModel.ofSize(pageSizeMoreThenTotalRecords));
        assertThat(pageModel.totalPages()).isEqualTo(1);
        assertThat(pageModel.totalItems()).isEqualTo(totalRecords);
        assertThat(pageModel.content()).hasSize(totalRecords);
    }

    @Test
    void givingPageSizeEqualsToTotalRecord_findAll_totalPageShouldBeOne() {
        var totalRecords = (int) personDao.totalCount();
        var pageModel = personDao.findAll(PageRequestModel.ofSize(totalRecords));
        assertThat(pageModel.totalPages()).isEqualTo(1);
        assertThat(pageModel.totalItems()).isEqualTo(totalRecords);
        assertThat(pageModel.content()).hasSize(totalRecords);
    }

    @Test
    void givingPageSizeLessThenTotalRecord_findAll_totalPageShouldBeOne() {
        var totalRecords = (int) personDao.totalCount();
        var pageModel = personDao.findAll(PageRequestModel.ofSize(totalRecords - 1));
        assertThat(pageModel.totalPages()).isEqualTo(2);
        assertThat(pageModel.totalItems()).isEqualTo(totalRecords);
        assertThat(pageModel.content()).hasSize(totalRecords - 1);
    }

    @Test
    void givingCurrentPageLessThen1_findAll_shouldGetEmptyPageModel() {
        int currentPage = -1;
        var pageModel = personDao.findAll(PageRequestModel.of(currentPage, 10));
        assertThat(pageModel).isNotNull();
        assertThat(pageModel.currentPage()).isEqualTo(0);
        assertThat(pageModel.totalItems()).isEqualTo(0);
        assertThat(pageModel.totalPages()).isEqualTo(0);
        assertThat(pageModel.numberOfElements()).isEqualTo(0);
        assertThat(pageModel.content()).isEmpty();
    }
}