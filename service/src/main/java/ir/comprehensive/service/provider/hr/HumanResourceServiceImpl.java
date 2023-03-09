package ir.comprehensive.service.provider.hr;

import ir.comprehensive.database.service.CategoryDao;
import ir.comprehensive.database.service.PersonDao;
import ir.comprehensive.domain.model.dto.HumanResourceInfo;
import ir.comprehensive.service.hr.HumanResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HumanResourceServiceImpl implements HumanResourceService {
    private final PersonDao personDao;
    private final CategoryDao categoryDao;

    @Override
    public HumanResourceInfo getInfo() {
        return new HumanResourceInfo(personDao.totalCount(), categoryDao.totalCount());
    }

    @Override
    public PersonDao getPersonDao() {
        return this.personDao;
    }

    @Override
    public CategoryDao getCategoryDao() {
        return this.categoryDao;
    }
}
