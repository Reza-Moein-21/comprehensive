package ir.comprehensive.service.test.provider.hr;

import ir.comprehensive.database.service.CategoryDao;
import ir.comprehensive.database.service.PersonDao;
import ir.comprehensive.service.hr.HumanResourceService;
import ir.comprehensive.service.provider.hr.HumanResourceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class HumanResourceServiceImplTest {
    private HumanResourceService humanResourceService;

    @Mock
    PersonDao personDao;
    @Mock
    CategoryDao categoryDao;

    @BeforeEach
    void setUp() {
        humanResourceService = new HumanResourceServiceImpl(personDao,categoryDao);
    }

    @Test
    void givingMockDao_getInfo_shouldGetExpectedDTO() {
        var info = humanResourceService.getInfo();
        assertThat(info)
                .isNotNull()
                .matches(hr-> hr.personCount() == 0)
                .matches(hr-> hr.categoryCount() == 0);
    }
}