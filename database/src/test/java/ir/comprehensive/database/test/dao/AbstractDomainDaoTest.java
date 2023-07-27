package ir.comprehensive.database.test.dao;

import ir.comprehensive.common.exception.ExceptionTemplate;
import ir.comprehensive.database.exception.DeletingException;
import ir.comprehensive.database.exception.SearchingException;
import ir.comprehensive.database.model.PageModel;
import ir.comprehensive.database.model.PageRequestModel;
import ir.comprehensive.database.model.SearchCriteria;
import ir.comprehensive.database.internal.mapper.PersonMapper;
import ir.comprehensive.database.internal.dao.impl.AbstractDomainDao;
import ir.comprehensive.database.internal.dao.impl.PersonDaoImpl;
import ir.comprehensive.database.test.utils.DBExtension;
import ir.comprehensive.database.test.utils.Sql;
import ir.comprehensive.domain.model.PersonModel;
import org.jooq.DSLContext;
import org.jooq.generated.tables.records.PersonRecord;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Sql("/schema-init.sql")
@ExtendWith(DBExtension.class)
class AbstractDomainDaoTest {

    private final AbstractDomainDao<PersonModel, PersonRecord, Long> abstractDomainDao;

    AbstractDomainDaoTest(DSLContext context, PersonMapper mapper) {
        this.abstractDomainDao = new PersonDaoImpl(context, mapper);
    }

    @Nested
    @Sql("/schema-init.sql")
    class DMLTest {

        @Test
        void givingTwoPersonRecordWithNoRelation_deleteAll_shouldDeleteTowRecord() {
            int numberOfDeleted = abstractDomainDao.deleteAll(Set.of(987654221L, 678954321L));
            assertThat(numberOfDeleted).isEqualTo(2);
            Optional<PersonModel> deletedPerson = abstractDomainDao.findById(987654221L);
            assertThat(deletedPerson).isNotPresent();
        }

        @Test
        void givingOnePersonRecordWithNoRelation_deleteOne_shouldDeleteOneRecord() {
            int numberOfDeleted = abstractDomainDao.deleteById(987654221L);
            assertThat(numberOfDeleted).isEqualTo(1);
            Optional<PersonModel> deletedPerson = abstractDomainDao.findById(987654221L);
            assertThat(deletedPerson).isNotPresent();
        }

        @Test
        void givingPersonRecordWithRelation_deleteOne_shouldTrowDeletingException() {
            var expectedId = 987654321L;
            assertThatThrownBy(() -> abstractDomainDao.deleteById(expectedId))
                    .isInstanceOf(ExceptionTemplate.class)
                    .isInstanceOf(DeletingException.class)
                    .matches(t -> ((ExceptionTemplate) t).getCode().equals("23503"));

            var notDeletedPerson = abstractDomainDao.findById(expectedId);
            assertThat(notDeletedPerson).isPresent();
        }

        @Test
        void givingPersonModelWithRelation_deleteOne_shouldTrowDeletingException() {
            var expectedId = 987654321L;
            var model = new PersonModel(expectedId, null, null, null, null, null, null, null, null);
            assertThatThrownBy(() -> abstractDomainDao.delete(model))
                    .isInstanceOf(ExceptionTemplate.class)
                    .isInstanceOf(DeletingException.class)
                    .matches(t -> ((ExceptionTemplate) t).getCode().equals("23503"));

            var notDeletedPerson = abstractDomainDao.findById(expectedId);
            assertThat(notDeletedPerson).isPresent();
        }

        @Test
        void givingMoreThenOnePersonRecordWithRelation_deleteAll_shouldTrowDeletingException() {
            var recordIdOne = 987612345L;
            var recordIdTwo = 934543526L;
            assertThatThrownBy(() -> abstractDomainDao.deleteAll(Set.of(recordIdOne, recordIdTwo)))
                    .isInstanceOf(ExceptionTemplate.class)
                    .isInstanceOf(DeletingException.class)
                    .matches(t -> ((ExceptionTemplate) t).getCode().equals("23503"));

            var notDeletedPerson = abstractDomainDao.findById(recordIdOne);
            assertThat(notDeletedPerson).isPresent();

            notDeletedPerson = abstractDomainDao.findById(recordIdTwo);
            assertThat(notDeletedPerson).isPresent();
        }
    }

    @Test
    void giving10Record_totalCount_shouldReturn10() {
        assertThat(abstractDomainDao.totalCount()).isEqualTo(10);
    }

    @Nested
    @Sql("/schema-init.sql")
    class FindQueryTest {
        @Test
        void givingValidPersonRecord_findById_ShouldGetExpectedRecord() {
            var expectedId = 987654321L;
            var personOptional = abstractDomainDao.findById(expectedId);
            assertThat(personOptional)
                    .isPresent()
                    .get()
                    .matches(p -> p.id().equals(expectedId));
        }

        @Test
        void givingPageSizeMoreThenTotalRecord_findAll_totalPageShouldBeOne() {
            var totalRecords = abstractDomainDao.totalCount();
            var pageSizeMoreThenTotalRecords = (totalRecords * 10);
            PageModel<PersonModel> pageModel = abstractDomainDao.findAll(PageRequestModel.ofSize(pageSizeMoreThenTotalRecords));
            assertThat(pageModel.totalPages()).isEqualTo(1);
            assertThat(pageModel.totalItems()).isEqualTo(totalRecords);
            assertThat(pageModel.content()).hasSize(totalRecords);
        }

        @Test
        void givingPageSizeEqualsToTotalRecord_findAll_totalPageShouldBeOne() {
            var totalRecords = abstractDomainDao.totalCount();
            var pageModel = abstractDomainDao.findAll(PageRequestModel.ofSize(totalRecords));
            assertThat(pageModel.totalPages()).isEqualTo(1);
            assertThat(pageModel.totalItems()).isEqualTo(totalRecords);
            assertThat(pageModel.content()).hasSize(totalRecords);
        }

        @Test
        void givingPageSizeLessThenTotalRecord_findAll_totalPageShouldBeOne() {
            var totalRecords = abstractDomainDao.totalCount();
            var pageModel = abstractDomainDao.findAll(PageRequestModel.ofSize(totalRecords - 1));
            assertThat(pageModel.totalPages()).isEqualTo(2);
            assertThat(pageModel.totalItems()).isEqualTo(totalRecords);
            assertThat(pageModel.content()).hasSize(totalRecords - 1);
        }

        @Test
        void givingCurrentPageLessThen1_findAll_shouldGetEmptyPageModel() {
            int currentPage = -1;
            var pageModel = abstractDomainDao.findAll(PageRequestModel.of(currentPage, 10));
            assertThat(pageModel).isNotNull();
            assertThat(pageModel.currentPage()).isEqualTo(0);
            assertThat(pageModel.totalItems()).isEqualTo(0);
            assertThat(pageModel.totalPages()).isEqualTo(0);
            assertThat(pageModel.numberOfElements()).isEqualTo(0);
            assertThat(pageModel.content()).isEmpty();
        }

    }

    @Nested
    @Sql("/schema-init.sql")
    class SearchQueryTest {
        @Test
        void givingValidPersons_search_shouldGetListOfFilteredItems() {
            List<PersonModel> searchResult = abstractDomainDao
                    .search(
                            SearchCriteria.ofIgnoreNull("id", SearchCriteria.Type.EQUALS, 934543526L),
                            SearchCriteria.ofIgnoreNull("email", SearchCriteria.Type.LIKE, "hashem")
                    );
            assertThat(searchResult)
                    .isNotNull()
                    .isNotEmpty()
                    .hasSize(1);

            assertThat(searchResult.get(0).id()).isEqualTo(934543526L);
        }

        @Test
        void givingNoSearchCriteria_search_shouldGetEmpty() {
            var searchResult = abstractDomainDao.search();
            assertThat(searchResult).isNotNull().isEmpty();
            searchResult = abstractDomainDao.search(null);
            assertThat(searchResult).isNotNull().isEmpty();
        }

        @Test
        void givingInvalidPropertyPath_search_shouldThrowSearchException() {
            assertThatThrownBy(() -> abstractDomainDao.search(SearchCriteria.ofIgnoreNull("??", SearchCriteria.Type.EQUALS, 934543526L)))
                    .isInstanceOf(SearchingException.class)
                    .matches(i -> ((SearchingException) i).getCode().equals("100"));
            assertThatThrownBy(() -> abstractDomainDao.search(
                    SearchCriteria.ofIgnoreNull("id", SearchCriteria.Type.EQUALS, 934543526L),
                    SearchCriteria.ofIgnoreNull(null, SearchCriteria.Type.EQUALS, 934543526L)
            ))
                    .isInstanceOf(SearchingException.class)
                    .matches(i -> ((SearchingException) i).getCode().equals("100"));
        }
    }

}