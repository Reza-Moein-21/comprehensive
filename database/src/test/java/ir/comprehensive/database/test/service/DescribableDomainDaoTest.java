package ir.comprehensive.database.test.service;

import ir.comprehensive.database.exception.SearchingException;
import ir.comprehensive.database.model.PageRequestModel;
import ir.comprehensive.database.provider.config.JooqConfig;
import ir.comprehensive.database.provider.mapper.WarehouseCategoryMapper;
import ir.comprehensive.database.provider.mapper.WarehouseCategoryMapperImpl;
import ir.comprehensive.database.provider.service.AbstractDescribableDomainDao;
import ir.comprehensive.database.service.base.DescribableDomainDao;
import ir.comprehensive.domain.model.WarehouseCategoryModel;
import ir.comprehensive.domain.model.base.DomainModel;
import org.jooq.DSLContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.jooq.generated.tables.WarehouseCategory.WAREHOUSE_CATEGORY;

@Sql(scripts = {"classpath:schema-drop.sql", "classpath:schema-create.sql", "classpath:schema-init.sql"})
@SpringJUnitConfig({WarehouseCategoryMapperImpl.class, JooqConfig.class})
public class DescribableDomainDaoTest {

    DescribableDomainDao<WarehouseCategoryModel, Long> describableDomainDao;

    @Autowired
    private DSLContext context;
    @Autowired
    private WarehouseCategoryMapper mapper;

    @BeforeEach
    void setUp() {
        describableDomainDao = new AbstractDescribableDomainDao<>(context,
                mapper,
                WAREHOUSE_CATEGORY,
                WAREHOUSE_CATEGORY.ID) {
        };
    }

    @Test
    void givingNullTitle_findAllByTitle_shouldReturnEmptyPage() {
        final var result = describableDomainDao.findAllByTitle(null, PageRequestModel.ofSize(10));
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    void givingNullPageRequest_findAllByTitle_shouldThrowSearchingException() {
        assertThatThrownBy(() -> describableDomainDao.findAllByTitle("Other", null))
                .isInstanceOf(SearchingException.class)
                .matches(i -> ((SearchingException) i).getCode().equals("101"));
    }

    @Test
    void givingNoneExistTitle_findAllByTitle_shouldReturnEmptyPage() {
        final var result = describableDomainDao.findAllByTitle("<>", PageRequestModel.ofSize(10));
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    void givingValidTitleWithIgnoreCase_findAllByTitle_shouldReturnOneItem() {
        final var result = describableDomainDao.findAllByTitle("oThEr", PageRequestModel.ofSize(10));
        assertThat(result).isNotNull();
        assertThat(result.totalItems()).isEqualTo(4);
        assertThat(result.currentPage()).isOne();
        assertThat(result.totalPages()).isOne();
        assertThat(result.numberOfElements()).isOne();
        assertThat(result.content()).hasSize(1);
        assertThat(result.content().get(0).getId()).isEqualTo(79438295L);
    }

    @Test
    void givingTitleThatIncludeInTwoItem_findAllByTitle_shouldReturnTwoItem() {
        final var alef = "ا";
        final var result = describableDomainDao.findAllByTitle(alef, PageRequestModel.ofSize(10));
        assertThat(result).isNotNull();
        assertThat(result.totalItems()).isEqualTo(4);
        assertThat(result.currentPage()).isOne();
        assertThat(result.totalPages()).isOne();
        assertThat(result.numberOfElements()).isEqualTo(2);
        assertThat(result.content()).hasSize(2);
        assertThat(result.content())
                .map(DomainModel::getId)
                .contains(8793452L, 98342565L);

    }


    @Test
    void givingNullDescription_findAllByDescription_shouldReturnOneItem() {
        final var result = describableDomainDao.findAllByDescription(null, PageRequestModel.ofSize(10));
        assertThat(result).isNotNull();
        assertThat(result.totalItems()).isEqualTo(4);
        assertThat(result.currentPage()).isOne();
        assertThat(result.totalPages()).isOne();
        assertThat(result.numberOfElements()).isOne();
        assertThat(result.content()).hasSize(1);
        assertThat(result.content().get(0).getId()).isEqualTo(79438295L);
    }

    @Test
    void givingNullPageRequest_findAllByDescription_shouldThrowSearchingException() {
        final var validDescription = "دست افزار مخصوص";
        assertThatThrownBy(() -> describableDomainDao.findAllByDescription(validDescription, null))
                .isInstanceOf(SearchingException.class)
                .matches(i -> ((SearchingException) i).getCode().equals("101"));
    }

    @Test
    void givingNoneExistDescription_findAllByDescription_shouldReturnEmptyPage() {
        final var result = describableDomainDao.findAllByDescription("<>", PageRequestModel.ofSize(10));
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    void givingDescriptionThatIncludeInTwoItem_findAllByDescription_shouldReturnTwoItem() {
        final var mime = "م";
        final var result = describableDomainDao.findAllByDescription(mime, PageRequestModel.ofSize(10));
        assertThat(result).isNotNull();
        assertThat(result.totalItems()).isEqualTo(4);
        assertThat(result.currentPage()).isOne();
        assertThat(result.totalPages()).isOne();
        assertThat(result.numberOfElements()).isEqualTo(2);
        assertThat(result.content()).hasSize(2);
        assertThat(result.content())
                .map(DomainModel::getId)
                .contains(8793452L, 98342565L);
    }

    @Test
    void givingEmptyDescription_findAllByDescription_shouldReturnThreeItem() {
        final var result = describableDomainDao.findAllByDescription("", PageRequestModel.ofSize(10));
        assertThat(result).isNotNull();
        assertThat(result.totalItems()).isEqualTo(4);
        assertThat(result.currentPage()).isOne();
        assertThat(result.totalPages()).isOne();
        assertThat(result.numberOfElements()).isEqualTo(3);
        assertThat(result.content()).hasSize(3);
        assertThat(result.content())
                .map(DomainModel::getId)
                .contains(8793452L, 98342565L, 79438294L);
    }
}
